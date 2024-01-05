package org.boboj.core.batch.utils;

import cn.hutool.core.util.StrUtil;
import groovy.lang.GroovyClassLoader;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;

import org.boboj.core.batch.dto.AnnotationDto;
import org.boboj.core.batch.dto.ColumnDto;
import org.boboj.core.batch.param.RunTimeClassParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2024/1/2 16:09
 * @detail
 */
public class DynamicGeneicClassUtil {
    @SneakyThrows
    public static Class<?> createClass(String className, String packagePath, List<AnnotationDto> annotationDtos, List<ColumnDto> columnDtos) {
        packagePath = "package " + packagePath;
        String property = "private {propertyType} {propertyName};\n";
        String propertySet = "public void set{PropertyName}({propertyType} {propertyName}){\n" + "this.{propertyName}={propertyName};\n" + "}\n";
        String propertyGet = "public {propertyType} get{PropertyName}(){\n" + "return this.{propertyName};\n" + "}\n";
        ArrayList<String> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(columnDtos)) {
            for (ColumnDto columnDto : columnDtos) {
                HashMap<String, String> map = new HashMap<>();
                map.put("propertyName", columnDto.getPropertyName());
                map.put("propertyType", columnDto.getPropertyType());
                map.put("PropertyName", StrUtil.upperFirst(columnDto.getPropertyName()));
                if (!Objects.isNull(columnDto.getAnnotations())) {
                    List<String> collect = columnDto.getAnnotations().stream().map(AnnotationDto::getName).collect(Collectors.toList());
                    list.addAll(collect);
                }

                list.add(StrUtil.format(property, map));
                list.add(StrUtil.format(propertySet, map));
                list.add(StrUtil.format(propertyGet, map));
            }
        }
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        StringBuilder classString = new StringBuilder();
        classString.append(packagePath).append(";\n");
        if (CollectionUtils.isNotEmpty(annotationDtos)) {
            for (AnnotationDto annotationDto : annotationDtos) {
                classString.append(annotationDto.getPackageInfo()).append(";\n");
            }
        }
        if (CollectionUtils.isNotEmpty(columnDtos)) {
            for (ColumnDto columnDto : columnDtos) {
                List<AnnotationDto> annotations = columnDto.getAnnotations();
                if (CollectionUtils.isNotEmpty(annotations)) {
                    for (AnnotationDto annotation : annotations) {
                        classString.append(annotation.getPackageInfo()).append(";\n");
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(annotationDtos)) {
            for (AnnotationDto annotationDto : annotationDtos) {
                classString.append(annotationDto.getName()).append("\n");
            }
        }
        classString.append("\n").append("public class ").append(StrUtil.upperFirst(StrUtil.toCamelCase(className))).append("{\n");
        classString.append(StrUtil.join("\n", list)).append("}\n");
        Class<?> clazz = groovyClassLoader.parseClass(classString.toString());
        return clazz;
    }

    public static Class getParamsClass(RunTimeClassParam runClassParam) {
        Class<?> user = createClass(runClassParam.getClassName(), runClassParam.getPackagePath(), runClassParam.getAnnotationDtoList(), runClassParam.getColumnDtoList());
        return user;
    }
}
