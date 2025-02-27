package com.example.xddemo.demo.jgt;

import cn.hutool.crypto.SecureUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xuedong
 * Date: 2025/2/27
 */
@Setter
@Getter
public class SignDemo {


    public static void main(String[] args) {

        sign();

    }



    public static void sign() {
        String appKey = "f2xrf6xdmgwz4p61";
        String appSecret = "hytflj8yox7k6ejzb4s5xztx34qwqu2p";
        //身份证号码，AES加密后参与加签
        String patientCertNo="3MrXcUtIgAOJ9LFRSKwUZsshYwXV01koBZxRezAKqsY=";
        //毫秒级时间戳
        String timestamp = String.valueOf(System.currentTimeMillis());
        System.out.println("timestamp:" + timestamp);
        String signBody = appKey + "&" + timestamp + "&" +  patientCertNo + "&" + appSecret;
        String sign = SecureUtil.md5(signBody);
        System.out.println("sign:" + sign);
    }

}
