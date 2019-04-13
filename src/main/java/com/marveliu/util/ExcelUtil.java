package com.marveliu.util;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.marveliu.Cons;
import com.marveliu.model.Word;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;


/**
 * @Author: Marveliu
 * @Date: 2019/4/12 5:18 PM
 * @Description:
 **/

public class ExcelUtil {

    public static void writeWordsToExcel(List<Word> words) throws Exception {
        OutputStream out = new FileOutputStream(Cons.OUTPUT_XLS);
        try {
            ExcelWriter writer = EasyExcelFactory.getWriter(out);
            Sheet sheet1 = new Sheet(1, 0, Word.class);
            sheet1.setSheetName("sheet1");
            sheet1.setAutoWidth(Boolean.TRUE);
            writer.write(words, sheet1);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }


}
