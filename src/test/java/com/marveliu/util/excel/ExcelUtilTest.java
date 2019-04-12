package com.marveliu.util.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.marveliu.model.Doc;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class ExcelUtilTest {


    @Test
    public void TestRead() throws IOException {
        InputStream inputStream = new FileInputStream("summary.xlsx");
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 1, Doc.class));
        inputStream.close();
        print(data);
    }

    public void print(List<Object> datas) {
        int i = 0;
        for (Object ob : datas) {
            System.out.println(i++);
            System.out.println(ob);
        }
    }
}