package org.boboj.core.batch.writer;

import org.boboj.core.batch.model.Student;
import org.boboj.core.batch.writer.excel.XlsxFileWriter;
import org.boboj.core.batch.writer.excel.batch.item.file.XlsxFileItemWriter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;

import java.io.File;
import java.io.IOException;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/23 0:25
 */
public class TestClass {
//    public static void main(String[] args) {
//
//        String[] strings =new String[3];
//
//        strings[0] = "0";
//        strings[1] = "1";
//        strings[2] = "2";
////        strings[3] = "3";
////        strings[4] = "4";
//
//
//        System.out.println(strings[0]);
//        System.out.println(strings[1]);
//        System.out.println(strings[2]);
////        System.out.println(strings[3]);
////        System.out.println(strings[4]);
//
//
//    }

    public void writeExcel() throws IOException, InvalidFormatException  {
        FlatFileItemWriter flatFileItemWriter = new FlatFileItemWriter();

        String writeFilePath = "";
        File writeFile = new File(writeFilePath);

        if (writeFile.exists()) {
            writeFile.delete();
        }
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(writeFile);
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(xssfWorkbook);

    }

    private ItemWriter<Student> writer() {

        return new FlatFileItemWriter<Student>();

    }
    private ItemWriter<Student> writer2() {


        XlsxFileWriter<Student> studentXlsxFileItemWriter = new XlsxFileWriter<>();
        return new XlsxFileWriter<Student>();

    }
//
//    public static void main(String[] args) {
//
//        System.out.println(XlsxDefautThemeConstant.TITLE_FONT_SIZE);
//        System.out.println(XlsxDefautThemeConstant.TITLE_BORDER_TYPE);
//        Student student = new Student();
//        TestClass testClass = new TestClass();
//
//        testClass.aaa(student);
//
//    }
//    public void aaa(Object data) {
//        Class<?> aClass = data.getClass();


//        System.out.println("getName()"+data.getClass().getName());
//        System.out.println(""+);
//        System.out.println(""+);
//        System.out.println(""+);
//        System.out.println(""+);
//    }



}
