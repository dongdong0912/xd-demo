package com.example.xddemo.demo;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * Author: xuedong
 * Date: 2024/2/27
 */
public class Base64Demo {


    public static void main(String[] args) throws IOException {

        byte[] bytes = FileUtil.readBytes("/Users/xuedong/Desktop/kr_temp_report.pdf");

        String encode = cn.hutool.core.codec.Base64.encode(bytes);

        byte[] decode = cn.hutool.core.codec.Base64.decode(encode);


        File file = FileUtil.writeBytes(bytes,"/Users/xuedong/Desktop/123456654.pdf");


        //base64ToFile(null, "/Users/xuedong/Desktop/67809.pdf");

    }


    public static void base64ToFile(String base64Str, String filePath) throws IOException {
        // 解码 Base64 字符串
        byte[] decodedBytes = Base64.getDecoder().decode(base64Str);

        // 创建输出文件
        File file = new File(filePath);

        // 将解码后的字节写入文件
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(decodedBytes);
        }
    }


}

