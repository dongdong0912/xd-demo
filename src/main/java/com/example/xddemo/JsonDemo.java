package com.example.xddemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.TreeMap;

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

    public static void main(String[] args) {

        String json = "{\"a\":\"a\",\"c\":\"c\",\"b\":{\"e\":\"e\"}}";
        String result = flattenAndSort(json);
        System.out.println(result); // 输出: a=ab=e=ec=c


    }
}
