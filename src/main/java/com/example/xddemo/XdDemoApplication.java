package com.example.xddemo;

import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.example.xddemo", exclude = {RedisAutoConfiguration.class, RocketMQAutoConfiguration.class})
// 扫描cn.hutool.extra.spring包下所有类并注册之
@ComponentScan(basePackages={"cn.hutool.extra.spring","com.example.xddemo"})
public class XdDemoApplication {





    public static void main(String[] args) {

        SpringApplication.run(XdDemoApplication.class, args);
        System.out.println();


    }


    public static boolean checkConditions(String str) {
        if (str.length() < 8) {
            return false;
        }
        int conditionsMet = 0;
        // 条件1：字符串包含大写字母或者小写字母
        if (str.matches(".*[a-z].*") || str.matches(".*[A-Z].*")) {
            conditionsMet++;
        }
        // 条件2：字符串包含数字
        if (str.matches(".*\\d.*")) {
            conditionsMet++;
        }

        // 条件3：字符串包含特殊字符
        if (str.matches(".*[^a-zA-Z0-9].*")) {
            conditionsMet++;
        }

        // 至少满足两个条件即为正确
        return conditionsMet >= 2;
    }

}
