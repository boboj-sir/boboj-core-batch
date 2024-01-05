package org.boboj.core.batch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2024/1/2 16:57
 * @detail
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnDto {
    private String propertyName;
    private String propertyType;
    private List<AnnotationDto> annotations;
}