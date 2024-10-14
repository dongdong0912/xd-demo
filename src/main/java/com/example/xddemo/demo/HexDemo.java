package com.example.xddemo.demo;

import com.google.common.collect.Lists;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Author: xuedong
 * Date: 2023/12/19
 * @author xuedong
 */
public class HexDemo {


    public static void main(String[] args) throws UnsupportedEncodingException {


        byte[] buff = new byte[20];


        String hexString = "7B012001101266000544005C11000B0504037D";

        // 使用 DatatypeConverter 解析十六进制字符串为字节数组
        byte[] byteArray = DatatypeConverter.parseHexBinary(hexString);

        String a=new String(byteArray, StandardCharsets.UTF_8);
        byte  aa= (byte) 0xFF;
        byte aaa=0x1;
        System.out.println(a);


        String s = hexStringToAscii("7B");

        System.out.println(s);

        // 打印字节数组的内容
        System.out.println("字节数组: " + byteArrayToString(byteArray));
    }


    private static String byteArrayToString(byte[] byteArray) {
        StringBuilder result = new StringBuilder();
        List<String> list= Lists.newArrayList();
        for (byte b : byteArray) {
            result.append(String.format("%02X ", b));
            list.add(String.format("%02X ", b));
        }
        System.out.println(list.toString());
        return result.toString().trim();
    }


    private static String hexStringToAscii(String hexString) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < hexString.length(); i += 2) {
            // 从十六进制字符串中提取两个字符
            String hexPair = hexString.substring(i, i + 2);

            // 将两个字符转换为十进制整数
            int decimalValue = Integer.parseInt(hexPair, 16);

            // 将整数转换为 ASCII 字符并追加到输出字符串
            output.append((char) decimalValue);
        }
        return output.toString();
    }
}
