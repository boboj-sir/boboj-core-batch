package org.boboj.core.batch.writer.excel.batch.item.support;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellStyle;
import org.boboj.core.batch.constant.config.excel.XlsxDefautWriteConstant;
import org.boboj.core.batch.constant.config.excel.support.IXlsxDataContentSettings;
import org.boboj.core.batch.constant.config.excel.support.IXlsxDataStyleSettings;
import org.boboj.core.batch.constant.enums.XlsxIndexRangeEnum;
import org.boboj.core.batch.utils.StringUtil;
import org.boboj.core.batch.writer.excel.batch.item.IXlsxItemStyle;
import org.boboj.core.batch.writer.excel.batch.item.XlsxItemStyleWriter;
import org.boboj.core.batch.writer.excel.batch.item.exception.ContentWriterException;
import org.boboj.core.batch.writer.excel.batch.item.file.ExcelLineAggregator;
import org.boboj.core.batch.writer.excel.batch.item.file.ResourceAwareItemWriterItemStyle;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.WriterNotOpenException;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.ResourceAwareItemWriterItemStream;
import org.springframework.batch.item.support.AbstractFileItemWriter;
import org.springframework.batch.item.util.FileUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Objects;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/23 0:49
 */
public abstract class AbstractXlsxFileItemWriter<T> extends AbstractXlsxItemStreamItemWriter<T> implements ResourceAwareItemWriterItemStream<T>, ResourceAwareItemWriterItemStyle<T>, InitializingBean {

    private Resource resource;
    private String encoding = "UTF-8";

    protected IXlsxItemStyle xlsxItemStyle;

    private SXSSFWorkbook sxssfWorkbook;

    protected SXSSFSheet currentSheet;

    protected boolean shouldDeleteIfExists = true;

    /**
     * 允许追加 不同的 SheetData
     */
    protected boolean append = false;
    /**
     * 正在追加
     */
    private boolean appending = false;

    private int rowAccessWindowSize = 0;

    private ExcelLineAggregator<T> excelLineAggregator;
    protected OutputState state = null;
    public IXlsxDataStyleSettings xlsxDataStyle;
    public IXlsxDataContentSettings xlsxDataContentSettings;

    private String sheetName;

    protected AbstractXlsxFileItemWriter(IXlsxDataStyleSettings xlsxDataStyle) {
        this.rowAccessWindowSize = XlsxDefautWriteConstant.ROW_ACCESS_WINDOW_SIEZ;
        this.xlsxDataStyle = xlsxDataStyle;
        this.xlsxDataContentSettings = xlsxDataStyle.getXlsxDataContentSettings();


    }

    protected AbstractXlsxFileItemWriter(IXlsxDataStyleSettings xlsxDataStyle, String sheetName) {
        this.rowAccessWindowSize = XlsxDefautWriteConstant.ROW_ACCESS_WINDOW_SIEZ;
        this.xlsxDataStyle = xlsxDataStyle;
        this.sheetName = sheetName;
        this.xlsxDataContentSettings = xlsxDataStyle.getXlsxDataContentSettings();

    }

    public void setExcelLineAggregator(ExcelLineAggregator<T> excelLineAggregator) {
        this.excelLineAggregator = excelLineAggregator;
    }

    public void setRowAccessWindowSize(int rowAccessWindowSize) {
        this.rowAccessWindowSize = rowAccessWindowSize;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
        try {
            System.out.println(resource.getFile().getAbsolutePath());
        } catch (Exception ex) {

        }


        FlatFileItemWriter fileItemWriter = null;
    }

//    public void update(ExecutionContext executionContext) {
//        System.out.println("-----------------进入了Update");
//    }

    public void setAppend(boolean append) {
        this.append = append;
    }

    public void setShouldDeleteIfExists(boolean shouldDeleteIfExists) {
        this.shouldDeleteIfExists = shouldDeleteIfExists;
    }


    public void write(List<? extends T> items) throws Exception {
        if (!this.getOutputState().isInitialized()) {
            throw new WriterNotOpenException("Writer must be open before it can be written to");
        } else {

            this.state.write(items);
        }


    }

    /**
     *
     */
    public void close() {
        System.out.println("-----------------进入了close");
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(resource.getFile()))) {

            sxssfWorkbook.write(bufferedOutputStream);
            bufferedOutputStream.flush();
            sxssfWorkbook.dispose(); //调用删除临时文件
            sxssfWorkbook.close();
        } catch (IOException ioe) {
            throw new ItemStreamException("Unable to close thr ItemWriter", ioe);
        }

    }

    public void adjustAfterCompletion() {

    }

//    protected abstract String doWrite(List<? extends T> var1);


    /**
     * 自动生成SheetName的规则方法
     *
     * @param sourceName
     * @param sNumber
     * @return
     */
    protected abstract String generateSheetName(String sourceName, String sNumber);

    public void open(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("-----------------进入了open");
        super.open(executionContext);
        Assert.notNull(this.resource, "The resource must be set");
        if (!this.getOutputState().isInitialized()) {
            this.doOpen(executionContext);
        }

    }

    private void doOpen(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("-----------------进入了doOpen");
        OutputState outputState = this.getOutputState();
        try {
            outputState.initializeSxssfWorkbookWriter();
        } catch (IOException var5) {
            throw new ItemStreamException("Failed to initialize writer", var5);
        }


    }

    protected OutputState getOutputState() {
        if (this.state == null) {
            File file;
            try {
                file = this.resource.getFile();
            } catch (IOException var3) {
                throw new ItemStreamException("Could not convert resource to file: [" + this.resource + "]", var3);
            }

            Assert.state(!file.exists() || file.canWrite(), "Resource is not writable: [" + this.resource + "]");
            this.state = new OutputState();
            this.state.setDeleteIfExists(this.shouldDeleteIfExists);
            this.state.setEncoding(this.encoding);
            this.state.setxIndex(xlsxDataContentSettings.getContentStartCoordinate()[0]);
            this.state.setyIndex(xlsxDataContentSettings.getContentStartCoordinate()[1]);
        }

        return this.state;
    }

    protected abstract void generateTitles();

    protected class OutputState {

        FileChannel fileChannel;
        AbstractFileItemWriter abstractFileItemWriter = null;
        String encoding = "UTF-8";
        boolean restarted = false;
        long lastMarkedByteOffsetPosition = 0L;
        long linesWritten = 0L;
        boolean shouldDeleteIfExists = true;
        boolean initialized = false;


        int xIndex = 0;
        int yIndex = 1;
        private Integer sameSheetNum = 0;

        public void setDeleteIfExists(boolean shouldDeleteIfExists) {
            this.shouldDeleteIfExists = shouldDeleteIfExists;
        }

        public boolean isInitialized() {
            return this.initialized;
        }

        public void setEncoding(String encoding) {
            this.encoding = encoding;
        }

        public void setxIndex(int xIndex) {
            this.xIndex = xIndex;
        }

        public void setyIndex(int yIndex) {
            this.yIndex = yIndex;
        }

        /**
         * 批量写入数据
         * 处理内容：
         * - 主要写入数据到 当前Sheet
         * - （单Sheet 数据量达标| 需要重新开Sheet 写入数据对象）判断是否需要切换Sheet
         * -
         *
         * @param items
         * @throws IOException
         */
        public void write(List<? extends T> items) throws IOException {
            //当获取到的 lastRowNum= -1时,代表Sheet刚创建,没有一行数据
//            int createRowIndex = currentSheet.getLastRowNum() + 1; //即将创建的行的索引

            if (!this.initialized) {
                this.initializeSxssfWorkbookWriter();
            }

            // 数据写入过程达到最大Sheet行容纳量 则初始化Sheet
            for (int i = 0; i < items.size(); i++) {
                //每写一行数据 查看一次即将创建的Row是否大于最大允许的行索引
                if (yIndex > xlsxDataStyle.getRowContentEndIndex()) {
                    //相同sheet内容 计数+1
                    sameSheetNum++;
                    switchSheet(sameSheetNum.toString());
                    //自动切换Sheet后 重置开始创建的行索引
//                    createRowIndex = currentSheet.getLastRowNum() + 1;
                    yIndex = xlsxDataContentSettings.getContentStartCoordinate()[1];

                }
                T item = items.get(i);
                SXSSFRow currentRow = currentSheet.getRow(yIndex);
                if (Objects.isNull(currentRow)) {
                    currentRow = currentSheet.createRow(yIndex);
                }

                Object[] cellDataList = excelLineAggregator.aggregate(item);
                if (cellDataList.length > xlsxDataStyle.getColumnContentEndIndex()+1) {
                    throw new ContentWriterException("超出【xlsxDataStyle】设置的最大列数【" + (xlsxDataStyle.getColumnContentEndIndex() +1)+ "】！当前列数：" + cellDataList.length);
                }
                for (int j = 0; j < cellDataList.length; j++) {
                    String cellValue = cellDataList[j].toString();
                    SXSSFCell currentRowCell = currentRow.getCell(xIndex);
                    if (Objects.isNull(currentRowCell)) {
                        currentRowCell = currentRow.createCell(xIndex);
                    }

                    currentRowCell.setCellValue(cellValue);
                    currentRowCell.setCellStyle(xlsxItemStyle.getContentCellStyle());
                    xIndex++;
                }
                xIndex = xlsxDataContentSettings.getContentStartCoordinate()[0];
                yIndex++;
            }
        }

        private CellStyle cellStyle;

        /**
         * 初始化SXSSFWorkBook 对象
         * 参数 1 是文件追加|文件覆盖
         * 参数 2 标记workbook 已 initialize
         *
         * @throws IOException
         */
        private void initializeSxssfWorkbookWriter() throws IOException {
            File file = AbstractXlsxFileItemWriter.this.resource.getFile();
            FileUtils.setUpOutputFile(file, this.restarted, append, this.shouldDeleteIfExists);

            XSSFWorkbook xssfWorkbook = getXssfWorkbook(file.getAbsolutePath());
            sxssfWorkbook = new SXSSFWorkbook(xssfWorkbook, rowAccessWindowSize);
            if (sxssfWorkbook.getSheet(sheetName) != null) {
                System.out.println("获取到Sheet:" + sheetName);
            }

            //workbook 创建之后初始化其主题
            initializeThemeStyle();
            if (StringUtil.isEmpty(sheetName)) {
                currentSheet = sxssfWorkbook.createSheet();
            } else {
                currentSheet = sxssfWorkbook.createSheet(sheetName);
            }
            //每次初始化 workbook后新建的Sheet 重置内容开始行
            xIndex = xlsxDataContentSettings.getContentStartCoordinate()[0];
            yIndex = xlsxDataContentSettings.getContentStartCoordinate()[1];
            generateTitles();

            if (append && file.length() > 0L) {
                appending = true;
            }

            Assert.state(sxssfWorkbook != null, "Unable to initialize buffered writer");

            this.initialized = true;

        }

        private void switchSheet(String sNumber) {
            currentSheet = sxssfWorkbook.createSheet(generateSheetName(sheetName, sNumber));
            if (!xlsxDataStyle.isEnableAutomaticPagingAddTitle()) {
                return;
            }
            generateTitles();
        }

        /**
         * 参数 1 标记Sheet 已 initizlize
         * 参数 2 处理表头对象（行|列）
         */
        private void initializeSxssfSheet() throws ContentWriterException {

            throw new ContentWriterException("故意抛出错误！");
        }

        private void initializeThemeStyle() throws ContentWriterException {

            Assert.notNull(sxssfWorkbook, "initializeThemeStyle ,SXSSFWorkbook is null");
//            xlsxItemStyle = new XlsxItemStyleWriter(sxssfWorkbook);
//            xlsxItemStyle.updateXlsxThemeConstant(new XlsxDefautThemeConstant());
            xlsxItemStyle = new XlsxItemStyleWriter(sxssfWorkbook);
        }


        public XSSFWorkbook getXssfWorkbook(String filePath) {
            System.out.println(filePath);
            XSSFWorkbook workbook = null;
            BufferedOutputStream outputStream = null;
            try {
                File xlsxFile = new File(filePath);
                //允许追加
                if (append) {
                    if (xlsxFile.exists() && xlsxFile.length() > 0) {
                        FileInputStream file = new FileInputStream(xlsxFile);
                        workbook = new XSSFWorkbook(file);
                    } else {
                        workbook = new XSSFWorkbook();
                    }
                } else {
                    workbook = new XSSFWorkbook();
                }
//                BufferedInputStream bufferedInputStream = new BufferedInputStream();
//                FileOutputStream outputStream1 = new FileOutputStream(fileXlsxPath,append);
//                outputStream = new BufferedOutputStream(outputStream1);
//                workbook = new XSSFWorkbook();

//                FileInputStream file = new FileInputStream(fileXlsxPath);

                //Create Workbook instance holding reference to .xlsx file
//                workbook = new XSSFWorkbook(file);
//                workbook.write(outputStream);
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return workbook;
        }

    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        String filePath = "E:\\Tempory\\AAA\\DDD.xlsx";
//        initializeSxssfSheet();
        File file = new File(filePath);
//        if (!file.exists()) {
//            file.createNewFile();
//        } else {
//            file.delete();
//            file.createNewFile();
//
//        }
//        FileInputStream file1 = new FileInputStream(file);
//        XSSFWorkbook workbook = new XSSFWorkbook(file);

        long startTime = System.currentTimeMillis();

        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(getXssfWorkbook(filePath), 3000);
        sxssfWorkbook.setCompressTempFiles(true);
        SXSSFSheet sheet = sxssfWorkbook.createSheet("测试4");
        BufferedOutputStream outputStream = null;
        int createRowIndex = 0;
        for (int i = 0; i < 2000000; i++) {
            if (createRowIndex >= 1000000) {
                sheet = sxssfWorkbook.createSheet();
                createRowIndex = 0;
            }
            SXSSFRow row = sheet.createRow(createRowIndex);
            for (int j = 0; j < 10; j++) {
                SXSSFCell cell = row.createCell(j);
                cell.setCellValue("我是{" + createRowIndex + "}行 {" + j + "}列");
            }
            createRowIndex++;
            System.out.println("数据 行" + i);

        }
        outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
        sxssfWorkbook.write(outputStream);
        if (outputStream != null) {
            outputStream.close();

        }
        if (sxssfWorkbook != null) {
            sxssfWorkbook.close();
        }

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;
        System.out.println(">>>>>>>>>>>>>>>>>>>共计耗时：" + resultTime / 1000 + "s");
    }

    public static XSSFWorkbook getXssfWorkbook(String filePath) {
        System.out.println(filePath);
        XSSFWorkbook workbook = null;
        BufferedOutputStream outputStream = null;
        try {
            File fileXlsxPath = new File(filePath);
            FileInputStream inputStreamReader = new FileInputStream(fileXlsxPath);
//            outputStream = new BufferedOutputStream(new FileOutputStream(fileXlsxPath), 3000);
            OPCPackage pkg = OPCPackage.open(filePath);
            workbook = new XSSFWorkbook(fileXlsxPath);
//            workbook = (XSSFWorkbook) StreamingReader.builder().rowCacheSize(1000).bufferSize(8192).open(inputStreamReader);

//            workbook = new XSSFWorkbook(fileXlsxPath);
            System.out.println("getNumberOfSheets   " + workbook.getNumberOfSheets());
//            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return workbook;
    }

    /**
     * 参数 1 标记Sheet 已 initizlize
     * 参数 2 处理表头对象（行|列）
     */
    private static void initializeSxssfSheet() throws ContentWriterException {

        throw new ContentWriterException("故意抛出错误！");
    }

}


