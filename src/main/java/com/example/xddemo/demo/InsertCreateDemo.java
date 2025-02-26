package com.example.xddemo.demo;

import cn.hutool.core.io.FileUtil;
import org.checkerframework.checker.units.qual.K;

import java.io.File;
import java.util.List;

/**
 * Author: xuedong
 * Date: 2024/7/16
 */
public class InsertCreateDemo {


    public static final String COMMA = ",";


    public static void main(String[] args) throws Throwable {


        // 指定要读取的文件路径
        String filePath = "/Users/xuedong/Desktop/export_result.txt";

        // 使用FileUtil按行读取文件
        List<String> lines = FileUtil.readLines(new File(filePath), "UTF-8");

        for (String line : lines) {
            String[] split = line.split(COMMA);
            String id = generate(split[0]);
            String name = generate(split[1]);
            String hospital_id = generate(split[2]);
            String hospital_name = generate(split[3]);
            String is_online = split[4];

            String r = "(" + id + COMMA + name + COMMA + hospital_id + COMMA + hospital_name + COMMA + is_online + COMMA + "0" + "," + "now()" + "," + "now()" + ")" + COMMA;
            System.out.println(r);
        }


    }


    private static String generate(String content) {
        return String.format("'%s'", content.trim());
    }

}






