package org.boboj.core.batch.writer.excel.batch.item;

public interface LineAggregator<T> {
    Object[] aggregate(T item);
}
