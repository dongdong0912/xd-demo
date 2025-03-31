package com.example.xddemo.demo;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.xddemo.bo.UserImportBO;
import com.google.common.collect.Lists;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 我的患者导出有问题的文件
 * Author: xuedong
 * Date: 2023/12/12
 */
public class ExcelDemo {


    public static void main(String[] args) {


        Optional<Object> empty = Optional.empty();

        if (empty.isPresent()){
            System.out.println("有值");
        }else {
            System.out.println("无值");
        }


    }


    private static void test1() {
        String json = "";
        List<UserImportBO> userImportBOS = JSONArray.parseArray(json, UserImportBO.class);
        // 指定本地文件路径
        String filePath = "/Users/xuedong/Desktop/output.xlsx";
        EasyExcel.write(filePath, UserImportBO.class).sheet("Sheet1").doWrite(userImportBOS);
    }
}
