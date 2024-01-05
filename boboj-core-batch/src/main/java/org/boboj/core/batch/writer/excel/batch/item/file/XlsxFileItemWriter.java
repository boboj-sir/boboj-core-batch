package org.boboj.core.batch.writer.excel.batch.item.file;

import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.boboj.core.batch.constant.config.excel.support.IXlsxDataStyleSettings;
import org.boboj.core.batch.constant.config.excel.support.IXlsxDataTitlesSettings;
import org.boboj.core.batch.writer.excel.batch.item.support.AbstractXlsxFileItemWriter;
import org.springframework.util.Assert;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/23 0:52
 */
public class XlsxFileItemWriter<T> extends AbstractXlsxFileItemWriter<T> {

    private Map<Integer, String[]> titlesMap = new HashMap<>();

    public XlsxFileItemWriter(IXlsxDataStyleSettings xlsxDataStyle) {
        super(xlsxDataStyle);

    }
    public XlsxFileItemWriter(IXlsxDataStyleSettings xlsxDataStyle, String sheetName) {
        super(xlsxDataStyle,sheetName);

    }
    public XlsxFileItemWriter(IXlsxDataStyleSettings xlsxDataStyle, String sheetName, Map<Integer, String[]> titlesMap) {
        super(xlsxDataStyle,sheetName);
        this.titlesMap = titlesMap;

    }


    public String doWrite(List<? extends T> list)  {


//        System.out.println("————————————————————————————进入了doWrite");
//        Iterator var3 = list.iterator();
//
//        while(var3.hasNext()) {
//            Object item = var3.next();
//            System.out.println(item);
//        }

        return null;
    }


    public void afterPropertiesSet() throws Exception {

    }

    public void setFile(File file) {

    }


    /**
     * SheetName生成的默认方法
     * @param sourceName 源sheetName名称
     * @param sNumber 续接 源Sheet的名称的序号
     * @return
     */
    @Override
    public String generateSheetName(String sourceName, String sNumber) {
        Assert.notNull(xlsxDataStyle, "iXlsxDataStyle 为Null！无法获取Sheet数据参数配置!无法正确生成SheetName!");
        return super.xlsxDataStyle.getAutomaticTurnSheetNameRules().replace("${sourceName}",sourceName).
                replace("${sNumber}", sNumber);
    }

    @Override
    protected void generateTitles() {

        IXlsxDataTitlesSettings xlsxDataTitlesSettings = xlsxDataStyle.getXlsxDataTitlesSettings();
        //行的起始坐标
        int[] titleColumnStartCoordinate = xlsxDataTitlesSettings.getTitleColumnStartCoordinate();
        int cX = titleColumnStartCoordinate[0];
        int cY = titleColumnStartCoordinate[1];
        // 列的起始坐标
        int[] titleRowStartCoordinate = xlsxDataTitlesSettings.getTitleRowStartCoordinate();
        int rX = titleRowStartCoordinate[0];
        int rY = titleRowStartCoordinate[1];
        int cXIndex = cX;
        int cYIndex = cY;

        int rXIndex = rX;
        int rYIndex = rY;
        //
        for (Map.Entry<Integer,String[]> entry: titlesMap.entrySet()
             ) {
            String[] titles = entry.getValue();
            Integer titlesId = entry.getKey();


            if (titlesId > 0) { //处理 行表头
                SXSSFRow currentRow = currentSheet.getRow(rYIndex);
                if (Objects.isNull(currentRow)) {
                    currentRow = currentSheet.createRow(rYIndex);
                }
                for (int i = 0; i < titles.length; i++) {
                    String cellValue = titles[i];

                    SXSSFCell currentCell = currentRow.getCell(rXIndex);
                    if (Objects.isNull(currentCell)) {
                        currentCell = currentRow.createCell(rXIndex);
                    }
                    currentCell.setCellValue(cellValue);
                    currentCell.setCellStyle(xlsxItemStyle.getTitleCellStyle());
                    rXIndex++;
                }
                rXIndex = rX;//重置列开始索引
                rYIndex++;
            } else if(titlesId < 0){ //处理列表头
                for (int i = 0; i < titles.length; i++) {
                    String cellValue = titles[i];
                    SXSSFRow currentRow=currentSheet.getRow(cYIndex);
                    if (Objects.isNull(currentRow)) {
                        currentRow = currentSheet.createRow(cYIndex);
                    }

                    SXSSFCell currentCell = currentRow.getCell(cXIndex);
                    if (Objects.isNull(currentCell)) {
                        currentCell = currentRow.createCell(cXIndex);
                    }
                    currentCell.setCellValue(cellValue);
                    currentCell.setCellStyle(xlsxItemStyle.getTitleCellStyle());


                    cYIndex++;
                }
                cYIndex = cY;
                cXIndex++;
            };

        }
    }

}
