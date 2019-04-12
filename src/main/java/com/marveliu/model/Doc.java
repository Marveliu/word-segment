package com.marveliu.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @Author: Marveliu
 * @Date: 2019/4/12 5:40 PM
 * @Description: 从Excel中获得文件列表
 **/

public class Doc extends BaseRowModel {

    @ExcelProperty(index = 0)
    private Integer no;

    @ExcelProperty(index = 1)
    private String title;

    public Doc() {
    }

    public Doc(Integer no, String title) {
        this.no = no;
        this.title = title;
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

    @Override
    public String toString() {
        return "Doc{" +
                "no=" + no +
                ", title='" + title + '\'' +
                '}';
    }
}
