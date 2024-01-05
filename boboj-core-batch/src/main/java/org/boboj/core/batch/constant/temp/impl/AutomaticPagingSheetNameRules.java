//package org.boboj.core.batch.constant.temp.impl;
//
//import org.boboj.core.batch.constant.config.excel.support.IXlsxDataStyle;
//import org.boboj.core.batch.constant.temp.IAutomaticPagingSheetNameRules;
//import org.boboj.core.batch.writer.excel.batch.item.support.AbstractXlsxFileItemWriter;
//import org.springframework.stereotype.Component;
//import org.springframework.util.Assert;
//
//import java.util.List;
//
///**
// * @author 钵钵鸡要似风~
// * @dateTime 2023/12/30 4:16
// * @detail
// */
//@Component
//public class AutomaticPagingSheetNameRules extends AbstractXlsxFileItemWriter  {
//
//
//    public String generateSheetName(String sourceName, String sNumber) {
//        Assert.notNull(iXlsxDataStyle, "iXlsxDataStyle 为Null！无法获取自动翻页SheetName生成规则");
//        return super.iXlsxDataStyle.getAutomaticTurnSheetNameRules().replace("${sNumber}", sNumber);
//    }
//
//
//
//    protected String doWrite(List var1) {
//        return null;
//    }
//
//
//    public void afterPropertiesSet() throws Exception {
//
//    }
//
//
////    public static void main(String[] args) {
////        AutomaticPagingSheetNameRules iAutomaticPagingSheetNameRules = new AutomaticPagingSheetNameRules();
////
////        System.out.println(iAutomaticPagingSheetNameRules.generateSheetName("老师信息-${sNumber}页 ${sNumber} ${sNumber}", "01"));
////        System.out.println(AutomaticPagingSheetNameRules.generateSheetName("老师信息-${sNumber}页 ${sNumber} ${sNumber}", "01"));
////
////        System.out.println(IAutomaticPagingSheetNameRules.generateSheetName("老师信息-${sNumber}页 ${sNumber} ${sNumber}", "01"));
////
////        IAutomaticPagingSheetNameRules nameRules = new AutomaticPagingSheetNameRules();
////        System.out.println(IAutomaticPagingSheetNameRules.generateSheetName("学生信息-${sNumber}页 ${sNumber} ${sNumber}", "01"));
////        System.out.println(IAutomaticPagingSheetNameRules.generateSheetName("学生信息（${sNumber}）页", "01"));
////    }
//
//}
