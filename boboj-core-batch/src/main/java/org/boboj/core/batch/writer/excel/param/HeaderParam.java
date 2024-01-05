package org.boboj.core.batch.writer.excel.param;

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
public class HeaderParam {
}

/***
 * 头部（列|行）参数：控制范围
 * 内容参数：控制范围
 *
 * 样式：单元格
 *          -单元格格式
 *              -边框
 *                  -样式
 *                  -颜色
 *              -字体
 *                  -字体
 *                  -字型
 *                  -字号
 *                  -特殊效果
 *              -对齐
 *                  -文本对齐方式
 *                  -文本控制
 *                  -从左到右（文字方向）
 *
 *
 * 自动分页Sheet：
 *              - 启用
 *              - 关闭
 * 多数据Sheet：
 *
 */