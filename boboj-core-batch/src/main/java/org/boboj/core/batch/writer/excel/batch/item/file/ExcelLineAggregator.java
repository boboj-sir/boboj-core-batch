package org.boboj.core.batch.writer.excel.batch.item.file;

import org.boboj.core.batch.writer.excel.batch.item.LineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.PassThroughFieldExtractor;
import org.springframework.util.Assert;

public class ExcelLineAggregator<T> implements LineAggregator<T> {

    private FieldExtractor<T> fieldExtractor=new PassThroughFieldExtractor<T>();

    @Override
    public Object[] aggregate(T item) {
        Assert.notNull(item);
        Object[] fields= this.fieldExtractor.extract(item);
        return fields;
    }
    public void  setFieldExtractor(FieldExtractor<T> fieldExtractor){
        this.fieldExtractor=fieldExtractor;

    }
}
