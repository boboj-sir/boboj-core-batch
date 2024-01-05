package org.boboj.core.batch.writer.excel.param.base;

import org.boboj.core.batch.writer.excel.param.base.cellformat.Alignment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.poi.ss.usermodel.Font;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/23 0:12
 */
@Getter
@Setter
@ToString
public class CellFormat {
    private Alignment alignment;
    private Font font;
}
