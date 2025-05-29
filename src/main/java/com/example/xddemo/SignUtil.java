/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.example.xddemo;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.example.xddemo.demo.Constant.APPSECRET;
import static com.example.xddemo.demo.Constant.SIGN_HEAD_LIST;


public class SignUtil {

    public static String getSign(Map<String, String> headers, Map<String, String> parameters, String appSecret) throws Exception {
        if (null == headers) {
            headers = new HashMap<>();
        }
        if (null == parameters) {
            parameters = new HashMap<>();
        }
        String sortStr = getSortParameter(headers, parameters);
        System.out.println("sing sortStr：" + sortStr);
        String target = APPSECRET + sortStr + appSecret;
        System.out.println("target target：" + target);
        return sign(target);
    }

    private static String sign(String target) throws Exception {
        if (StringUtils.isBlank(target)) {
            return null;
        }
        byte[] infoMd5 = encryptMD5(target.getBytes("UTF-8"));
        return byte2hex(infoMd5);
    }

    /**
     * md5加密
     *
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] encryptMD5(byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(data);
    }

    /**
     * 二进制转换为大写的十六进制
     *
     * @param bytes
     * @return
     */
    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(aByte & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    public static String getSortParameter(Map<String, String> headerMap, Map<String, String> parameMap) {
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (SIGN_HEAD_LIST.contains(key) && StringUtils.isNotBlank(value)) {
                map.put(key, value);
            }
        }
        for (Map.Entry<String, String> entry : parameMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (StringUtils.isNotBlank(value)) {
                map.put(key, value);
            }
        }
        String[] keys = map.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : keys) {
            String value = map.get(key);
            stringBuilder.append(key).append(value);
        }
        return stringBuilder.toString();
    }


    @SneakyThrows
    public static void main(String[] args) {
        String s="appsecretappkeywy7SsUiaf91828Osmethodguahao.cic.saike.savereq1iYdLwu1ExWJOsWkdmbm1adtmjIUZsXKz+Ry7gFQpvt9B/YPO6WSrY4EnYmQeENiadqCB8dexGI/qieRzqaXGuFrz6GTZS5/rx/YWdNykuvsO19yxo6DVLtUmNVrDHEqRHkaG0gOYahplTo9FFjZc+fhG6F1d/6vY7a5ag/stgt7Vq2FMdLdCm86+2StVxctJ5U4TqEAiZRMize/KO5hEuz8Ok5I1+tfkn4GXNNjG3owKuB4j1+WMKhnWxICMwldRd7pcU9mM3TKz807AdbZ+MucDfKhuibZ9Fkre/jciWrBvVj98r8XidhdltuqZi4F840sMVtyhX7TiCqsepHPyV2Tv/ReS/DmwVc5UeVr2DvTyFR/wvrO0wtA5Yv8ckzPK/ks6Y3qStN5b2Y9aELTCAH4GxWPKJQWw7fa40QmvIe86fyy8QUwVB/BfiInjNkVFnbmSc2AI8RTZl+rVcHBEfJtxHirr/9+slLpEgI5A9CPKyaHaxSipxpE8npBi229BcVCyUdBBwdxze5wKKy3TbixN6neLHY47jtn+zPoULcZS4GiaRClC92US1pNdNbNogD2oKp6c25T/MYDAn5+zDTTYPawMp91WXgvxSXbE3Zll3RcJbIhaWFgQSL+xNpEAmBo3rPgikT57QZVvJq529rcs+iLK1QQCNeBtnvNbHDSPx0zkWBmkmoKvTYXVXf/FPDMm7xDe5ueQvlTaP/tqc1IPPoUzLpWjekjDoR8KqMjqJlLb1OSm7x7iC5KHdfZiqsLEO67V4bjZM4E2Ll8Xhu/gBt3XWoT6mMQinpyTAVTm0Tre41kemmxOJhSB7T5l3Rqwa+jYLQKW+t2xNObolNf8tQ4MrghuMEdCac4Ed4/zryXnoI9m8qYnjAbA0SbWKhmKKPTPMWz9ZYnQI9CEQOAkzkzNy5R7aXW8LgfozGFU8+xiDHY2bUBtmXDRmEc+Shg0cmFFogJHm3DfgtX/lRZwvI+5+F7BYXcRcwv4pISZb11WPX/Y0pYLxGgFWi+ChV+KWlLeX8Hnk4IkK9zaxVdpdg47Ope6NKcM+87C6mVCXytXKYMhpSFHyKiZig8ixODM+6MDr6gm8lc6T6xSjqx0rTPaScBFWAZ4C/XUIwRwH4X+REKhmHUeo8RlykGaI7Q7uze15dm/AgiGl9WF0z4z9zFplqMsTMyepKBwfuXC4Eidx/s/RVORH0wJ82OOyH4zNk6J2LqdI4b5N7mP6SCsE92OhXBFXl4yLmECPGhfN5Zuu0gxw5/CaOU0EinZ8yb/Y2IUyEBYsnEqx2ML8C6sey80hoKCThdp2SH5/88ut5uJGHcLmvC7KDZQFVf77b+x8eP9rdXVcG7neSfW75faqB1iaDIGmPXfcZALvc=timestamp174826207007795a57SAU37G5797811v106lC36q1Nnca";
        byte[] infoMd5 = encryptMD5(s.getBytes("UTF-8"));
        String s1 = byte2hex(infoMd5);

        System.out.println(s1);

    }
}
