package com.marveliu.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @Author: Marveliu
 * @Date: 2019/4/12 7:32 PM
 * @Description: NLP分词
 **/

public class Word extends BaseRowModel {

    @ExcelProperty(value = "文件编号", index = 0)
    private Integer no;

    @ExcelProperty(value = "文件名称", index = 1)
    private String title;

    @ExcelProperty(value = "词汇", index = 2)
    private String content;

    @ExcelProperty(value = "词频", index = 3)
    private Integer freq;

    public Word(Integer no, String title, String content, Integer freq) {
        this.no = no;
        this.title = title;
        this.content = content;
        this.freq = freq;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFreq() {
        return freq;
    }

    public void setFreq(Integer freq) {
        this.freq = freq;
    }

    @Override
    public String toString() {
        return "Word{" +
                "no=" + no +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", freq=" + freq +
                '}';
    }
}
