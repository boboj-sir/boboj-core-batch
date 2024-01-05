package org.boboj.core.batch.constant.enums;

import org.boboj.core.batch.constant.ExcelConstant;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/23 0:12
 */
public enum XlsxNumRangeEnum {

    DATE_NONE_NUM(ExcelConstant.XLSX_SHEET_DATE_NONE_NUM),

    ROW_MAX_NUM(ExcelConstant.XLSX_ROW_MAX_NUM),
    COLUMN_MAX_NUM(ExcelConstant.XLSX_COLUMN_MAX_NUM);

    XlsxNumRangeEnum(int value) {
        this.value = value;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
