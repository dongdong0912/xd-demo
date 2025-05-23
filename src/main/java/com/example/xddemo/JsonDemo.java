package com.example.xddemo;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * @author xuedong
 * Date: 2025/4/11
 */
public class JsonDemo {


    public static String flattenAndSort(String jsonStr) {

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        Map<String, String> flatMap = new TreeMap<>();
        flatten(jsonObject, flatMap);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : flatMap.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return sb.toString();
    }

    private static void flatten(JSONObject jsonObject, Map<String, String> flatMap) {
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof JSONObject) {
                flatten((JSONObject) value, flatMap);
            } else {
                flatMap.put(key, value.toString());
            }
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {


        String a = "111111";

        List<String> strings = splitUtf8Safe(a, 3800);


        for (String string : strings) {
            System.out.println(string);
        }


    }

    public static List<String> splitUtf8Safe(String input, int maxBytesPerChunk) {
        List<String> result = new ArrayList<>();

        if (input == null || input.isEmpty()) {
            return result;
        }

        byte[] byteArray = input.getBytes(StandardCharsets.UTF_8);
        int length = byteArray.length;
        int offset = 0;

        while (offset < length) {
            int endIndex = Math.min(offset + maxBytesPerChunk, length);

            // 回退，确保不截断多字节字符（避免乱码）
            while (endIndex > offset &&
                    endIndex < length &&
                    isUtf8ContinuationByte(byteArray[endIndex])) {
                endIndex--;
            }

            // 防止无法回退，强制前进一段（极端情况下）
            if (endIndex == offset) {
                endIndex = Math.min(offset + maxBytesPerChunk, length);
            }

            byte[] chunk = Arrays.copyOfRange(byteArray, offset, endIndex);
            result.add(new String(chunk, StandardCharsets.UTF_8));

            offset = endIndex;
        }

        return result;
    }

    /**
     * 判断一个字节是否为 UTF-8 的 continuation byte（10xxxxxx）
     */
    private static boolean isUtf8ContinuationByte(byte b) {
        return (b & 0xC0) == 0x80;
    }





}
