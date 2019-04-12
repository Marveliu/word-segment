package com.marveliu.util.excel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author: Marveliu
 * @Date: 2019/4/12 5:42 PM
 * @Description:
 **/

@Retention(RetentionPolicy.SOURCE)
public @interface ExcelType {
    String HSSF = "org.apache.poi.hssf.usermodel.HSSFWorkbook";
    String XSSF = "org.apache.poi.xssf.usermodel.XSSFWorkbook";
    String SXSSF = "org.apache.poi.xssf.usermodel.XSSFWorkbook";
}

