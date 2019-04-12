package com.marveliu;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.google.common.io.Files;
import com.marveliu.model.Doc;
import com.marveliu.model.Word;
import com.marveliu.util.FileUtil;
import com.marveliu.util.HanlpUtil;
import com.marveliu.util.StringUtil;
import com.marveliu.util.excel.ExcelUtil;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * @Author: Marveliu
 * @Date: 2019/4/12 2:15 PM
 * @Description: 词频统计程序
 **/

public class App {

    // 词频结果
    static List<Word> summary = new ArrayList<>();
    // 文件列表
    static Map<Integer, String> docs = new HashMap<>();

    static {
        try {
            // 读取文件列表
            InputStream inputStream = new FileInputStream("summary.xlsx");
            EasyExcelFactory.read(inputStream, new Sheet(1, 1, Doc.class)).forEach(doc -> {
                Doc d = (Doc) doc;
                docs.put(d.getNo(), d.getTitle());
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        try {
            Map<String, Integer> words = new HashMap<>();
            // 对每个文件进行词频统计
            for (String s : FileUtil.datas) {
                if (Files.getFileExtension(s).equals(Cons.FILE_TYPE_TXT)) {
                    Integer n = Integer.valueOf(s.split("\\.")[0]);
                    Doc doc = new Doc(n, docs.get(n));
                    String content = FileUtil.getContent(s);
                    // 统计词频
                    HanlpUtil.procWords(content.trim()).forEach(word -> {
                        String w = word.word;
                        // 过滤非法字符
                        if (StringUtil.isValid(w)) {
                            words.putIfAbsent(w, 0);
                            words.put(w, words.get(w) + 1);
                        }
                    });
                    summary(words, doc);
                    words.clear();
                }
            }
            // 输出结果文件
            save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 统计高频词汇
     *
     * @param map
     * @param doc
     * @throws Exception
     */
    static void summary(Map<String, Integer> map, Doc doc) throws Exception {
        List<Word> words = new ArrayList<>();
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for (int i = 0; i < Cons.WORDS_NUMBER; i++) {
            words.add(new Word(doc.getNo(), doc.getTitle(), list.get(i).getKey(), list.get(i).getValue()));
        }
        summary.addAll(words);
    }

    /**
     * 导出excel
     *
     * @throws Exception
     */
    static void save() throws Exception {
        ExcelUtil.writeWordsToExcel(summary);
    }
}

