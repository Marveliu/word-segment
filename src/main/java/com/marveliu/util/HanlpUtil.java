package com.marveliu.util;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

import java.util.List;

/**
 * @Author: Marveliu
 * @Date: 2019/4/12 3:10 PM
 * @Description: HanLP工具类
 **/

public class HanlpUtil {

    /**
     * 分词，用于统计词频
     *
     * @param s
     * @return
     */
    public static List<Term> procWords(String s) {
        List<Term> termList = NLPTokenizer.segment(s);
        return termList;
    }

    /**
     * 获取关键词
     *
     * @param s
     * @return
     */
    public static List<String> procKeyWords(String s) {
        List<String> keywordList = HanLP.extractKeyword(s, 5);
        return keywordList;
    }

}
