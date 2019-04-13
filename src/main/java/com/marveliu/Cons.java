package com.marveliu;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.marveliu.model.Doc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Marveliu
 * @Date: 2019/4/12 2:37 PM
 * @Description: 配置文件
 **/

public class Cons {

    // public final static String BASE_PATH = Thread.currentThread().getContextClassLoader().getResource("").getPath() + File.separator;
    // public final static String FilesPath = BASE_PATH + "data";

    // 文档文件
    public final static String FilesPath = "docs";
    // 输出文件
    public final static String OUTPUT_XLS = "out.xlsx";

    // 文本格式
    public final static String FILE_TYPE_TXT = "txt";
    public final static String FILE_TYPE_DOC = "doc";
    public final static String FILE_TYPE_DOCX = "docx";

    // 词频数目
    public final static Integer WORDS_NUMBER = 20;


}
