package com.example.xddemo.spring;


import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author: xuedong
 * Date: 2024/8/26
 */
@Component
@Slf4j
public class RetryDemo {

    /**
     * 重试机制发送短信
     */
    @Retryable(
            maxAttempts = 4,
            backoff = @Backoff(delay = 2000L, multiplier = 2)
    )
    public boolean sendSmsRetry(Integer num) {
        log.info("[RetryComponent][sendSmsRetry]>>>> 当前时间：{}", getNowTime());

        try {
            return SmsUtil.sendSms(num);
        } finally {
            System.out.println(111);
        }
    }

    /**
     * 兜底方法，规则：
     * 1、超出了最大重试次数；
     * 2、抛出了不进行重试的异常；
     */
    @Recover
    public boolean recover() {
        log.info("[RetryComponent][recover]>>>> 短信发送次数过多，请稍后重试！");
        return false;
        // 模拟发生空指针界异常
        // throw new NullPointerException("空指针异常123");

    }

    /**
     * 获取当前时间
     */
    private String getNowTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


}
