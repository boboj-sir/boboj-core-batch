package org.boboj.core.batch.writer.excel.batch.item;


import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamSupport;
import org.springframework.batch.item.util.ExecutionContextUserSupport;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/23 0:45
 */
public abstract class XlsxItemStreamSupport implements XlsxItemStream {
    private final ExecutionContextUserSupport executionContextUserSupport = new ExecutionContextUserSupport();

    public XlsxItemStreamSupport() {
        ItemStreamSupport itemStreamSupport = null;
    }

    public void close() {
    }

    public void open(ExecutionContext executionContext) {
    }

    public void update(ExecutionContext executionContext) {
    }

    public void setName(String name) {
        this.setExecutionContextName(name);
    }

    public String getName() {
        return this.executionContextUserSupport.getName();
    }

    protected void setExecutionContextName(String name) {
        this.executionContextUserSupport.setName(name);
    }

    public String getExecutionContextKey(String key) {
        return this.executionContextUserSupport.getKey(key);
    }
}
