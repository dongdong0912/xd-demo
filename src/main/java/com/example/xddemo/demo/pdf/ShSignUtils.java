package com.example.xddemo.demo.pdf;

import com.wedoctor.cloud.open.api.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: xuedong
 * Date: 2024/10/24
 */
@Slf4j
public class ShSignUtils {

    public static String getSign(Map<String, String> headers, Map<String, String> parameters, String appSecret) throws Exception {
        if (null == headers) {
            headers = new HashMap<>();
        }

        headers.remove("message-id");
        parameters.remove("apiMethod");
        parameters.remove("apiProductCode");
        parameters.remove("apiReqUrl");
        if (null == parameters) {
            parameters = new HashMap<>();
        }
        String sortStr = getSortParameter(headers, parameters);
        String target = Constant.APPSECRET + sortStr + appSecret;
        log.info("签名原始串:{}", target);
        return sign(target);
    }

    private static String sign(String target) throws Exception {
        if (StringUtils.isBlank(target)) {
            return null;
        }
        byte[] infoMd5 = encryptMD5(target.getBytes(StandardCharsets.UTF_8));
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
            if (Constant.SIGN_HEAD_LIST.contains(key) && StringUtils.isNotBlank(value)) {
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
