/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.example.xddemo;

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
}
