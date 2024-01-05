package org.boboj.core.batch.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.boboj.core.batch.dto.AnnotationDto;
import org.boboj.core.batch.dto.ColumnDto;

import java.util.List;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2024/1/2 17:38
 * @detail
 */
@Getter
@Setter
@ToString
public class RunTimeClassParam {
    private String className;
    private String packagePath;
    /**
     * 类上注解
     */
    private List<AnnotationDto> annotationDtoList;
    /**
     * 字段内容 及其注释
     */
    private List<ColumnDto> columnDtoList;
}
