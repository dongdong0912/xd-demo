package com.example.xddemo.demo;

import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: xuedong
 * Date: 2024/9/3
 */
@Data
public class V1 {


    private static final Map<String, String> MAP = new ConcurrentHashMap<>();


    public void put(String a, String b) {
        MAP.put(a, b);
    }

    public void print() {
        System.out.println(JSONUtil.toJsonStr(MAP));
    }
}
