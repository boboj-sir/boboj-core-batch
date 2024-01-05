package org.boboj.core.batch.writer.excel.batch.item.exception;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/27 22:43
 */
public class ContentWriterException extends RuntimeException {
    public ContentWriterException(String message) {
        super(message);
    }

    public ContentWriterException(String msg, Throwable nested) {
        super(msg, nested);
    }

    public ContentWriterException(Throwable nested) {
        super(nested);
    }
}
