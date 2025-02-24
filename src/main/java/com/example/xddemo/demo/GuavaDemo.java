package com.example.xddemo.demo;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author xuedong
 * Date: 2024/12/25
 */
public class GuavaDemo {

    public static LoadingCache<Integer, String> cache = null;


    @SneakyThrows
    public static void main(String[] args) {


        cache = CacheBuilder.newBuilder()
                // 设置缓存的最大容量，超过最大容量时会自动淘汰旧的条目
                .maximumSize(100)
                // 设置写入后过期时间（单位：毫秒）
                .expireAfterWrite(10, TimeUnit.MINUTES)
                // 设置写入后刷新时间（单位：毫秒）
                .refreshAfterWrite(5, TimeUnit.MINUTES)
                // 设置并发级别，控制缓存的并发访问数量
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                // 定义缓存的加载逻辑
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer key)  {
                        // 模拟从外部源（如数据库）加载数据
                        System.out.println("加载数据: " + key);
                        //cache.invalidateAll(Collections.singletonList(key));
                        return null;
                    }
                });



        String s = cache.get(0);
        System.out.println(s);

        //cache.invalidateAll(Arrays.asList(0));


        System.out.println(cache.get(0) + "das");

        System.out.println(cache.asMap().toString() + "sdassdsd");

    }
}
