package com.example.xddemo.tonglian;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.net.URL;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author xuedong
 * Date: 2025/4/17
 */
public class Test {
    /**
     * 算法常量:SM3withSM2
     */
    public static final String ALGORITHM_SM3SM2_BCPROV = "SM3withSM2";
    private final static int SM3withSM2_RS_LEN = 32;


    public static void main(String[] args) throws Exception {

        String urlStr = "https://adc-dht-h5.freestyle-libre.cn/DHT/HcpPdf/e5d23bad-c916-4b07-ac18-a1907430f87b/報告1.pdf";

        String url2="http://10.60.10.19/local-kano/H8S449818";
        // 使用 java.net.URL 提取主机名
        URL url = new URL(url2);
        String host = url.getHost();

        System.out.println(host);

    }


    private static void publicTest() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Security.addProvider(new BouncyCastleProvider());

        String base64PublicKey = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAE/BnA8BawehBtH0ksPyayo4pmzL/u1FQ2sZcqwOp6bjVqQX4tjo930QAvHZPJ2eez8sCz/RYghcqv4LvMq+kloQ==";
        byte[] keyBytes = Base64.getDecoder().decode(base64PublicKey);

        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(keyBytes));

        System.out.println("公钥算法: " + publicKey.getAlgorithm());
        System.out.println("公钥格式: " + publicKey.getFormat());
        System.out.println("公钥详细信息: " + publicKey);
    }


    private static void test1() throws Exception {

        // 添加BC安全提供者
        Security.addProvider(new BouncyCastleProvider());

        // Base64 私钥字符串
        String base64PrivateKey = "MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQgNqz1EieIP8QVzV7vEmx5e8f7XN7/MIzoeXgEinxcG0agCgYIKoEcz1UBgi2hRANCAAQNfkEgaCQ4cdZ4aD2LWMcnkk5LALQfL05oY8x8XQDIyUM44N15YcTwtFNvHYgyeNRa93vlEUutp935n6rp4yuf";

        byte[] privKeyBytes = Base64.getDecoder().decode(base64PrivateKey);

        KeyFactory keyFactory = KeyFactory.getInstance("EC", "BC");
        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privKeyBytes));

        System.out.println("算法: " + privateKey.getAlgorithm());   // EC
        System.out.println("格式: " + privateKey.getFormat());     // PKCS#8
        System.out.println("私钥对象: " + privateKey);


    }


}
