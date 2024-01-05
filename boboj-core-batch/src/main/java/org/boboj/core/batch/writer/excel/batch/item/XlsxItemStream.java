package org.boboj.core.batch.writer.excel.batch.item;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/23 0:41
 */
public interface XlsxItemStream {
    void open(ExecutionContext var1) throws ItemStreamException;

    void update(ExecutionContext var1) throws ItemStreamException;

    void close() throws ItemStreamException;
}
