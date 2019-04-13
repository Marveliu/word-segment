package com.marveliu.util;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.marveliu.Cons;

import java.io.File;

/**
 * @Author: Marveliu
 * @Date: 2019/4/12 2:17 PM
 * @Description: 文件操作
 **/

public class FileUtil {


    public static String[] datas = null;

    static {
        // 获取所有文件
        File file = new File(Cons.FilesPath);
        datas = file.list();
    }

    /**
     * 读取数据文件内容
     */
    public static String getContent(String s) {
        try {
            String fp = Cons.FilesPath + File.separator + s;
            StringBuilder sb = new StringBuilder();
            Files.readLines(new File(fp), Charsets.UTF_8).forEach(line -> {
                sb.append(CharMatcher.whitespace().removeFrom(line));
            });
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
