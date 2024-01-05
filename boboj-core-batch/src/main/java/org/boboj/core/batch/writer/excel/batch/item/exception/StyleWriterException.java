package org.boboj.core.batch.writer.excel.batch.item.exception;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/27 22:43
 */
public class StyleWriterException extends RuntimeException {
    public StyleWriterException(String message) {
        super(message);
    }

    public StyleWriterException(String msg, Throwable nested) {
        super(msg, nested);
    }

    public StyleWriterException(Throwable nested) {
        super(nested);
    }
}
