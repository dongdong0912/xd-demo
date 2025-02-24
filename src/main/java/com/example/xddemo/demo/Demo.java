package com.example.xddemo.demo;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.List;

/**
 * Author: xuedong
 * Date: 2024/7/16
 */
public class Demo {


    public static void main(String[] args) throws Throwable {


        // 指定要读取的文件路径
        String filePath = "/Users/xuedong/Desktop/export_result.txt";

        // 使用FileUtil按行读取文件
        List<String> lines = FileUtil.readLines(new File(filePath), "UTF-8");

        for (String line : lines) {
            String[] split = line.split(",");
            String id = "'" + split[0].trim() + "'";
            String name = "'" + split[1].trim() + "'";
            String hospital_id = "'" + split[2].trim() + "'";
            String hospital_name = "'" + split[3].trim() + "'";
            String is_online = split[4];

            String r = "(" + id + "," + name + "," + hospital_id + "," + hospital_name + "," + is_online + "," + "0" + "," + "now()" + "," + "now()" + ")" + ",";
            System.out.println(r);
        }


    }


}






