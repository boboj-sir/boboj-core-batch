package org.boboj.core.batch.writer.excel.param.base.cellformat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/23 0:12
 */
@Getter
@Setter
@ToString
public class Border {
    private int lineStyle; // 默认|加粗|双线|虚线|...
    private int color;//颜色 蓝色|红色
}
