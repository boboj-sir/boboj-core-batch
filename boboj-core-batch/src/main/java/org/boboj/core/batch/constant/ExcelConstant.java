package org.boboj.core.batch.constant;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/23 0:12
 */
public interface ExcelConstant {


//##########################{  $ XLSX $ }#########################################
    Integer XLSX_ROW_MAX_INDEX = 1048575 ;
    Integer XLSX_COLUMN_MAX_INDEX = 16383;


    Integer XLSX_ROW_MAX_NUM = 1048576 ;
    Integer XLSX_COLUMN_MAX_NUM = 16384;
    Integer XLSX_SHEET_DATE_NONE_NUM = -1;

    Integer XLSX_SHEET_DATA_START_INDEX = 0;



//###########################{  $ XLS $ }########################################


    Integer XLS_ROW_MAX_INDEX = 65535 ;
    Integer XLS_COLUMN_MAX_INDEX = 255;

    Integer XLS_ROW_MAX_NUM = 65536 ;
    Integer XLS_COLUMN_MAX_NUM = 256;
}
