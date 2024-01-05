package org.boboj.core.batch.utils;

/**
 * @author 钵钵鸡要似风~
 * @dateTime 2023/12/30 2:06
 * @detail
 */
public class StringUtil {

    public static String bigHumpToSmall(String str) {
        String[] words = str.split("(?=[A-Z])");
        String camelCaseString = words[0].toLowerCase();
        for (int i = 1; i < words.length; i++) {
            camelCaseString += words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }
        return camelCaseString;
    }

    public static String toUppercaseAndRemoveSpaces(String str) {
        return str.toUpperCase().replace(" ","");

    }

    public static String convertPhrasesToUppercaseLetters(String str) {
        String[] words = str.split("\\s+");
        String camelCaseString = "";
        for (int i = 1; i < words.length; i++) {
            System.out.println(words[i]+"\n");
            camelCaseString += words[i].toUpperCase() + "_";
        }
        return camelCaseString.substring(0, camelCaseString.length() - 1);

    }


    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static void main(String[] args) {
        System.out.println(convertPhrasesToUppercaseLetters("Enable multiple data source appends"));
    }

}
