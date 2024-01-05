package org.boboj.core.batch.writer.excel.batch.item;

import org.boboj.core.batch.constant.config.XlsxTheme01Config;
import org.boboj.core.batch.constant.config.XlsxThemeDefautConfig;
import org.boboj.core.batch.constant.config.excel.support.IXlsxThemeSettings;

import org.boboj.core.batch.writer.excel.batch.item.exception.StyleWriterException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/27 22:39
 */
public class XlsxItemStyleWriter implements IXlsxItemStyle {


    private static SXSSFWorkbook sxssfWorkbook;

    private IXlsxThemeSettings xlsxTheme;
    private String titleFontName;
    private String contentFontName;
    private int titleFontSize;
    private int contentFontSize;
    private FillPatternType titleFillPatternType;
//    private FillPatternType contentFillPatternType;
    private int[] titleBackgroundColorRGB;


    private CellStyle titleCellStyle;
    private CellStyle contentCellStyle;

    public XlsxItemStyleWriter(SXSSFWorkbook sxssfWorkbook) {
        this.sxssfWorkbook = sxssfWorkbook;
        if (this.xlsxTheme == null) {
            // 配置成默认的主题
            this.xlsxTheme = new XlsxThemeDefautConfig();
        }
        this.init();
    }

    public XlsxItemStyleWriter(SXSSFWorkbook sxssfWorkbook, IXlsxThemeSettings xlsxTheme) {
        this.sxssfWorkbook = sxssfWorkbook;
        this.xlsxTheme = xlsxTheme;
        this.init();
    }

    public void updateXlsxThemeConstant(IXlsxThemeSettings xlsxTheme) {
        this.xlsxTheme = xlsxTheme;
        this.flush();
    }

    public CellStyle getTitleCellStyle() throws StyleWriterException {

        if (this.titleCellStyle != null) {
            return this.titleCellStyle;
        }

        //设置表头单元格背景颜色
        CellStyle cellStyle = commonStyle(sxssfWorkbook);
        XSSFColor titleBgColor = new XSSFColor(new java.awt.Color(titleBackgroundColorRGB[0],
                titleBackgroundColorRGB[1],
                titleBackgroundColorRGB[2]
        ), new DefaultIndexedColorMap());
//        cellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        ((XSSFCellStyle) cellStyle).setFillForegroundColor(titleBgColor);

        cellStyle.setFillPattern(titleFillPatternType);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //设置单元格内容自动换行
        cellStyle.setWrapText(true);

        //获取单元格的字体样式
        Font font = sxssfWorkbook.createFont();
        font.setFontName(titleFontName);
        font.setFontHeightInPoints((short) titleFontSize);
        //加粗
        font.setBold(true);

        cellStyle.setFont(font);
        this.titleCellStyle = cellStyle;
        return cellStyle;
    }

    public CellStyle getContentCellStyle() throws StyleWriterException {

        if (this.contentCellStyle != null) {
            return this.contentCellStyle;
        }
        CellStyle cellStyle = commonStyle(sxssfWorkbook);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        Font font = sxssfWorkbook.createFont();
        font.setFontName(contentFontName);
        font.setFontHeightInPoints((short) contentFontSize);
        cellStyle.setFont(font);
        XSSFColor titleBgColor = new XSSFColor(new java.awt.Color(titleBackgroundColorRGB[0],
                titleBackgroundColorRGB[1],
                titleBackgroundColorRGB[2]
        ), new DefaultIndexedColorMap());
        ((XSSFCellStyle) cellStyle).setFillForegroundColor(titleBgColor);
        this.contentCellStyle = cellStyle;
        return cellStyle;
    }


    /**
     * 公共 设置单元格内容 垂直对齐方式|边框颜色|边框样式
     *
     * @param workbook
     * @return
     */
    private CellStyle commonStyle(SXSSFWorkbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();

        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

        return cellStyle;
    }


    /**
     * 初始化 或者重置样式参数
     */
    public void init() {
        titleFontName = xlsxTheme.getTitleFontName();
        contentFontName = xlsxTheme.getContentFontName();
        titleFontSize = xlsxTheme.getTitleFontSize();
        contentFontSize = xlsxTheme.getContentFontSize();
        titleFillPatternType = xlsxTheme.getTitleFillPatternType();
//        contentBorderType = xlsxTheme.getContentBorderType();
        titleBackgroundColorRGB = xlsxTheme.getTitleFillBackgroundColorRgb();
    }

    private void flush() {
        init();
        //刷新后重置样式
        this.titleCellStyle = null;
        this.contentCellStyle = null;
    }

    public static void main(String[] args) {


//        XlsxThemeConstant xlsxDefautThemeConstant = new XlsxDefautThemeConstant();
//
//        XlsxThemeConstant xlsxTheme01Constant = new XlsxDefautThemeConstant(new XlsxTheme01Constant());
//        XlsxThemeConstant xlsxTheme02Constant = new XlsxDefautThemeConstant(new XlsxTheme02Constant());
//
//        System.out.println("xlsxDefautThemeConstant---"+xlsxDefautThemeConstant.getTitleFontSize());
//
//        System.out.println("xlsxTheme01Constant---"+xlsxTheme01Constant.getTitleFontSize());
//        System.out.println("xlsxTheme02Constant---"+xlsxTheme02Constant.getTitleFontSize());

        XlsxItemStyleWriter xlsxItemStyleWriter1 = new XlsxItemStyleWriter(new SXSSFWorkbook());
        System.out.println("xlsxItemStyleWriter1---" + xlsxItemStyleWriter1.contentFontSize);


        XlsxItemStyleWriter xlsxItemStyleWriter2 = new XlsxItemStyleWriter(new SXSSFWorkbook());

        xlsxItemStyleWriter1.updateXlsxThemeConstant(new XlsxTheme01Config());
        System.out.println("xlsxItemStyleWriter1---" + xlsxItemStyleWriter1.contentFontSize);
        xlsxItemStyleWriter1.updateXlsxThemeConstant(new XlsxThemeDefautConfig());
        System.out.println("xlsxItemStyleWriter1---" + xlsxItemStyleWriter1.contentFontSize);
        System.out.println("xlsxItemStyleWriter2---" + xlsxItemStyleWriter2.contentFontSize);
    }
}
