package com.example.xddemo.spring;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * Author: xuedong
 * Date: 2024/8/26
 */
@Slf4j
public class SmsUtil {

    /**
     * 发送短信
     */
    public static boolean sendSms(Integer num) {


        log.info("[SmsUtil][sendSms]>>>> random num = {}", num);
        if (Objects.equals(num, 0)) {
            // 模拟发生参数异常
            throw new IllegalArgumentException("参数有误！");
        }
        if (Objects.equals(num, 1)) {
            // 模拟发生数组越界异常
            throw new ArrayIndexOutOfBoundsException("数组越界！");
        }
        if (Objects.equals(num, 2)) {
            return true;
        }
        throw new NullPointerException();
    }

}
