package com.marveliu.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Marveliu
 * @Date: 2019/4/12 4:01 PM
 * @Description: 字符串处理，用于过滤词汇
 **/

public class StringUtil {

    static Pattern p = Pattern.compile("[\u4e00-\u9fa5]");

    /**
     * 判断是否为含有汉字
     *
     * @param s
     * @return
     */
    public static boolean hasChinese(String s) {
        Matcher m = p.matcher(s);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否全是汉字
     *
     * @param s
     * @return
     */
    public static boolean isChinese(String s) {
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            n = (int) s.charAt(i);
            if (!(19968 <= n && n < 40869)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 关键词是否合法
     * 1. 两个字符以上
     * 2. 都是中文
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        if (s.length() > 1 && isChinese(s)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "./";
        String s2 = "中国";
        String s3 = "中国。";
        System.out.println(isChinese(s1));
        System.out.println(isChinese(s2));
        System.out.println(isChinese(s3));
    }
}
