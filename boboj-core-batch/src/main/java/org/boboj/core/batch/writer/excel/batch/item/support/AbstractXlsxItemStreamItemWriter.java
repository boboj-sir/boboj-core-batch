package org.boboj.core.batch.writer.excel.batch.item.support;

import org.springframework.batch.item.ItemStreamSupport;
import org.springframework.batch.item.ItemStreamWriter;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/23 0:58
 */
public abstract class AbstractXlsxItemStreamItemWriter<T>  extends ItemStreamSupport implements ItemStreamWriter<T> {
    public AbstractXlsxItemStreamItemWriter() {
       //拓展 继承就从这里开始

    }

    public void switchSheet() {

    }
}
