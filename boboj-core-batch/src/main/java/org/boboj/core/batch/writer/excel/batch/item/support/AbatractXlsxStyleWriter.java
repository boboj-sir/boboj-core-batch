package org.boboj.core.batch.writer.excel.batch.item.support;//package org.boboj.core.batch.writer.excel.batch.item.support;

import org.boboj.core.batch.writer.excel.batch.item.exception.StyleWriterException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/25 9:54
 */
public  class AbatractXlsxStyleWriter {

   public   CellStyle getContentCellStyle(SXSSFWorkbook workbook) throws StyleWriterException{
       CellStyle cellStyle = allCellCommonStyle(workbook);
       Font font = workbook.createFont();
       font.setFontName("宋体");
       font.setFontHeightInPoints((short)11);
       cellStyle.setFont(font);
       cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
       return cellStyle;

    }

    public CellStyle getTitleCellStyle(SXSSFWorkbook workbook) throws StyleWriterException{
        //设置表头单元格背景颜色
        CellStyle cellStyle = allCellCommonStyle(workbook);
        XSSFColor titleBgColor=new XSSFColor(new java.awt.Color(155,194,230),new DefaultIndexedColorMap());
//        cellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        ((XSSFCellStyle)cellStyle).setFillForegroundColor(titleBgColor);

        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置单元格内容自动换行
        cellStyle.setWrapText(true);
        //获取单元格的字体样式
        Font font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short)11);
        //加粗
        font.setBold(true);
        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     * 公共
     * @param workbook
     * @return
     */
    private  CellStyle  allCellCommonStyle(SXSSFWorkbook workbook){
        CellStyle cellStyle=workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

        return  cellStyle;
    }

}
