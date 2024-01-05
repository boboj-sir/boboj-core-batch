package org.boboj.core.batch.writer.excel.batch.item;

import org.boboj.core.batch.writer.excel.batch.item.exception.StyleWriterException;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/27 22:53
 */
public interface IXlsxItemStyle {

    CellStyle getContentCellStyle() throws StyleWriterException;

    CellStyle getTitleCellStyle() throws StyleWriterException;

//    void updateXlsxThemeConstant(XlsxThemeConstant xlsxThemeConstant);
}
