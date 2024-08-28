package com.example.xddemo;

import com.example.xddemo.spring.RetryDemo;
import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.Executor;

@SpringBootApplication(scanBasePackages = "com.example.xddemo", exclude = {RedisAutoConfiguration.class, RocketMQAutoConfiguration.class})
// 扫描cn.hutool.extra.spring包下所有类并注册之
@ComponentScan(basePackages = {"cn.hutool.extra.spring", "com.example.xddemo"})
@EnableRetry

public class XdDemoApplication {


    @Resource
    private RetryDemo retryDemo;
    @Resource(name = "taskExecutor")
    private Executor threadPoolExecutor;


    public static void main(String[] args) {

        SpringApplication.run(XdDemoApplication.class, args);

    }

    @PostConstruct
    public void test() {
        threadPoolExecutor.execute(() -> {
            boolean b = retryDemo.sendSmsRetry(1);
            if (b) {
                System.out.println("测试完毕,正确");
            }
            if (!b) {
                System.out.println("测试完毕,错误");
            }
        });
        System.out.println("调用完成");
    }


}
