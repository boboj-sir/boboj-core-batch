package org.boboj.core.batch.constant.enums;

import org.boboj.core.batch.constant.config.XlsxDataStyleDefaultConfig;
import org.boboj.core.batch.constant.config.XlsxDataStyleNoneConfig;
import org.boboj.core.batch.constant.config.XlsxTheme01Config;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/30 2:59
 * @detail 主题样式 枚举类
 */
public enum XlsxThemeEnum {

    XLSX_THEME_NONE(-1,"xlsxThemeNoneConfig","前台配置参数使用的主题", XlsxDataStyleNoneConfig.class),
    XLSX_THEME_DEFAULT(0,"xlsxThemeDefautConfig","默认主题", XlsxDataStyleDefaultConfig.class),
    XLSX_THEME_01(1,"xlsxTheme01Config","主题01", XlsxTheme01Config.class);
    private int code;
    private String name;
    private String detail;
    private Class clz;

    XlsxThemeEnum(int code, String name, String detail,Class clz) {
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
