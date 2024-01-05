package org.boboj.core.batch.writer.excel;//package org.boboj.core.batch.writer.excel;

import org.boboj.core.batch.constant.config.XlsxDataContentSettingNoneConfig;
import org.boboj.core.batch.constant.config.XlsxDataStyleDefaultConfig;
import org.boboj.core.batch.constant.config.XlsxDataStyleNoneConfig;
import org.boboj.core.batch.constant.config.XlsxDataTitlesSettingNoneConfig;
import org.boboj.core.batch.constant.config.excel.support.IXlsxDataStyleSettings;
import org.boboj.core.batch.writer.excel.batch.item.file.XlsxFileItemWriter;

import java.util.Map;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/23 0:35
 */
public class XlsxFileWriter<T> extends XlsxFileItemWriter<T> {
    public XlsxFileWriter() {
        super(new XlsxDataStyleDefaultConfig());
    }
    public XlsxFileWriter(IXlsxDataStyleSettings iXlsxDataStyle) {
        super(iXlsxDataStyle);

    }
    public XlsxFileWriter(String sheetName,boolean append) {
        super(new XlsxDataStyleDefaultConfig(),sheetName);

        setAppend(append);

    }
    public XlsxFileWriter(String sheetName, boolean append, Map<Integer,String[]> titlesMap) {
                super(new XlsxDataStyleNoneConfig(new XlsxDataTitlesSettingNoneConfig(new int[]{2, 0}, new int[]{0, 2}),
                        new XlsxDataContentSettingNoneConfig(new int[]{2, 2}),true
                ), sheetName, titlesMap);
        setAppend(append);
    }
}
