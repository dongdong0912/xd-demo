package com.example.xddemo.demo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.xddemo.bo.UserImportBO;

import java.text.SimpleDateFormat;
import java.util.List;


/**
 * 我的患者导出有问题的文件
 * Author: xuedong
 * Date: 2023/12/12
 */
public class ExcelDemo {


    public static void main(String[] args) {





    }



    private static void test1() {
        String json="";
        List<UserImportBO> userImportBOS = JSONArray.parseArray(json, UserImportBO.class);
        // 指定本地文件路径
        String filePath = "/Users/xuedong/Desktop/output.xlsx";
        EasyExcel.write(filePath, UserImportBO.class).sheet("Sheet1").doWrite(userImportBOS);
    }
}
