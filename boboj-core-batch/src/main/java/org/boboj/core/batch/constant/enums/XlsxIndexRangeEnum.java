package org.boboj.core.batch.constant.enums;

import org.boboj.core.batch.constant.ExcelConstant;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/23 0:12
 */
public enum XlsxIndexRangeEnum {

    DATA_START_INDEX(ExcelConstant.XLSX_SHEET_DATA_START_INDEX),
    ROW_MAX_INDEX(ExcelConstant.XLSX_ROW_MAX_INDEX),
    COLUMN_MAX_INDEX(ExcelConstant.XLSX_COLUMN_MAX_INDEX);


    XlsxIndexRangeEnum(int value) {
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
