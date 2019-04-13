package com.marveliu;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.google.common.io.Files;
import com.marveliu.model.Doc;
import com.marveliu.model.Word;
import com.marveliu.util.ExcelUtil;
import com.marveliu.util.FileUtil;
import com.marveliu.util.HanlpUtil;
import com.marveliu.util.StringUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Marveliu
 * @Date: 2019/4/12 2:15 PM
 * @Description: 词频统计程序
 **/

public class App {

    // 文件列表
    public static Map<Integer, String> docs = new HashMap<>();
    static CountDownLatch count = null;
    static int nThread = Runtime.getRuntime().availableProcessors() + 1;
    static List<Word> words = new ArrayList<>();

    static {
        try {
            // 读取文件列表
            InputStream inputStream = new FileInputStream("summary.xlsx");
            EasyExcelFactory.read(inputStream, new Sheet(1, 1, Doc.class)).forEach(doc -> {
                Doc d = (Doc) doc;
                docs.put(d.getNo(), d.getTitle());
            });
            count = new CountDownLatch(docs.size());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        ExecutorService es = Executors.newFixedThreadPool(nThread);
        try {
            // 对每个文件进行词频统计
            for (String s : FileUtil.datas) {
                if (Files.getFileExtension(s).equals(Cons.FILE_TYPE_TXT)) {
                    es.execute(() -> {
                        long bd = System.currentTimeMillis();
                        String content = FileUtil.getContent(s);
                        Integer n = Integer.valueOf(s.split("\\.")[0]);
                        Doc doc = new Doc(n, App.docs.get(n));
                        Map<String, Integer> map = new HashMap<>();
                        // 统计词频
                        HanlpUtil.procWords(content.trim()).forEach(term -> {
                            String w = term.word;
                            // 过滤非法字符
                            if (StringUtil.isValid(w)) {
                                map.putIfAbsent(w, 0);
                                map.put(w, map.get(w) + 1);
                            }
                        });
                        synchronized (words) {
                            words.addAll(summary(map, doc));
                        }
                        System.out.printf("处理文件：%s，总共用时：%d ms \n", s, System.currentTimeMillis() - bd);
                        count.countDown();
                    });
                }
            }
            count.await();
            ExcelUtil.writeWordsToExcel(words);
            // 输出结果文件
            System.out.printf("使用时间：%d ms,处理文件：%d\n", System.currentTimeMillis() - begin, docs.size());
            es.shutdown();
            es.awaitTermination(1, TimeUnit.SECONDS);
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
    static List<Word> summary(Map<String, Integer> map, Doc doc) {
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
        return words;
    }

}

