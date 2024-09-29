package com.example.xddemo.demo;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: xuedong
 * Date: 2024/7/16
 */
public class Demo {


    private static final Map<String, Config> MAP = new ConcurrentHashMap<>();
    //创建一个容量为100000的LRU缓存
    private static final Cache<String, String> CACHE = CacheBuilder.newBuilder().maximumSize(100000).build();
    public static final Lock LOCK = new ReentrantLock();


    public static void main(String[] args) throws Throwable {


        // 定义两个列表
        List<String> list1 = Lists.newArrayList("A", "B", "C", "D");
        List<String> list2 = Lists.newArrayList("B", "D", "E", "F");

        // 将列表转换为Set
        Set<String> set1 = Sets.newHashSet(list1);
        Set<String> set2 = Sets.newHashSet(list2);

        // 求交集
        Set<String> intersection = Sets.intersection(set1, set2);

        System.out.println(intersection);

        String s = CACHE.getIfPresent("key");

        System.out.println(s);





    }






    private static Config getConfig() {
        Config config = MAP.get("mchId");
        if (Objects.nonNull(config)) {
            return config;
        }
        LOCK.lock();
        Config config2 = MAP.get("mchId");
        if (Objects.nonNull(config2)) {
            return config2;
        }
        // 使用自动更新平台证书的RSA配置
        // 一个商户号只能初始化一个配置，否则会因为重复的下载任务报错
        try {
            Config config1 =
                    new RSAAutoCertificateConfig.Builder()
                            .merchantId("")
                            .privateKeyFromPath("")
                            .merchantSerialNumber("")
                            .apiV3Key("")
                            .build();
            MAP.put("mchId", config1);
            return config1;
        } finally {
            LOCK.unlock();
        }

    }

}






