package org.boboj.core.batch.constant.enums;

import org.boboj.core.batch.constant.config.XlsxDataStyleDefaultConfig;
import org.boboj.core.batch.constant.config.XlsxDataStyleNoneConfig;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/30 2:59
 * @detail 数据格式样式 枚举类
 */
public enum XlsxDataStyleEnum {
    XLSX_DATA_STYLE_NONE(-1,"xlsxDataStyleNoneConfig","前台配置参数使用的 数据格式样式", XlsxDataStyleNoneConfig.class),
    XLSX_DATA_STYLE_DEFAULT(0,"xlsxDataStyleDefaultConfig","数据格式默认样式", XlsxDataStyleDefaultConfig.class);


    private int code;
    private String name;
    private String detail;
    private Class clz;

    XlsxDataStyleEnum(int code, String name, String detail,Class clz) {
        this.code = code;
        this.name = name;
        this.detail = detail;
        this.clz = clz;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Class getClz() {
        return clz;
    }

    public void setClz(Class clz) {
        this.clz = clz;
    }
}
