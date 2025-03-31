package com.example.xddemo.demo;

import cn.hutool.core.io.FileUtil;
import com.google.common.collect.Lists;

import java.io.File;
import java.util.List;

/**
 * Author: xuedong
 * Date: 2024/7/16
 */
public class InsertCreateDemo {


    public static final String COMMA = ",";

    public static final String INSERT = "INSERT INTO `std_supply` ( `id`, `name`, `hospital_id`, `hospital_name`, `is_online`, `is_major`, `gmt_created`, `gmt_modified` ) VALUES";


    public static void main(String[] args) throws Throwable {


        shandong_yongfa();

    }


    /**
     * 山东-给药途径
     */
    private static void shandong_yongfa() {
        String filePath = "/Users/xuedong/Desktop/shandong_yongfa.txt";
        // 使用FileUtil按行读取文件
        List<String> lines = FileUtil.readLines(new File(filePath), "UTF-8");
        for (String line : lines) {
            String[] split = line.split("\t");
            String code = split[0].trim();
            String name = split[1].trim();

            List<String> list = Lists.newArrayList();
            list.add(generate("ry_std_usage_H37010203715"));
            list.add(generate(code));
            list.add(generate(name));
            list.add(generate(""));
            list.add(generate(""));
            list.add("now()");
            list.add("now()");
            list.add(generate("9999-12-31 23:59:59.000"));
            list.add("0");
            String join = String.join(",", list);
            String r = "(" + join + ")" + COMMA;
            System.out.println(r);
        }


    }

    /**
     * 山东-频次
     */
    private static void shandong_pinci() {
        String filePath = "/Users/xuedong/Desktop/shandong_fre.txt";
        // 使用FileUtil按行读取文件
        List<String> lines = FileUtil.readLines(new File(filePath), "UTF-8");
        for (String line : lines) {
            String[] split = line.split("\t");
            String code = split[0].trim();
            String name = split[1].trim();

            List<String> list = Lists.newArrayList();
            list.add(generate("ry_std_frequency_H37010203715"));
            list.add(generate(code));
            list.add(generate(name));
            list.add(generate(""));
            list.add(generate(""));
            list.add("now()");
            list.add("now()");
            list.add(generate("9999-12-31 23:59:59.000"));
            list.add("0");

            String join = String.join(",", list);
            String r = "(" + join + ")" + COMMA;
            System.out.println(r);


        }
    }

    private static void extracted() {
        System.out.println(INSERT);
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

            List<String> list = Lists.newArrayList();
            list.add(id);
            list.add(name);
            list.add(hospital_id);
            list.add(hospital_name);
            list.add(is_online);
            list.add("0");
            list.add("now()");
            list.add("now()");

            String join = String.join(",", list);
            String r = "(" + join + ")" + COMMA;
            System.out.println(r);
        }
    }


    private static String generate(String content) {
        return String.format("'%s'", content.trim());
    }


}






