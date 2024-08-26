package com.example.xddemo.spring;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Author: xuedong
 * Date: 2024/8/26
 */
@Slf4j
public class SmsUtil {

    /**
     * 发送短信
     */
    public static boolean sendSms() {

        // 使用随机数模拟重试场景
        int num = RandomUtil.randomInt(4);
        log.info("[SmsUtil][sendSms]>>>> random num = {}", num);


        if (NumberUtil.equals(num, 0)) {
            // 模拟发生参数异常
            throw new IllegalArgumentException("参数有误！");
        }
        if (NumberUtil.equals(num, 1)) {
            // 模拟发生数组越界异常
            throw new ArrayIndexOutOfBoundsException("数组越界！");
        }
        if (NumberUtil.equals(num, 2)) {
            return true;
        }
        if (NumberUtil.equals(num, 3)) {
            // 模拟发生空指针界异常
            throw new NullPointerException();
        }
        return false;
    }

}
