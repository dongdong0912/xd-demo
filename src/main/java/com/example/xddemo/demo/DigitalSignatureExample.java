package com.example.xddemo.demo;

import java.security.*;
import java.nio.charset.StandardCharsets;

/**
 * @author xuedong
 */
public class DigitalSignatureExample {

    public static void main(String[] args) throws Exception {
        // 生成密钥对（包含私钥和公钥）
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // 要签名的原始消息
        String message = "This is a message to sign";

        // 初始化 Signature 对象，使用指定的算法，如 "SHA256withRSA"
        Signature signature = Signature.getInstance("SHA256withRSA");

        // 初始化签名对象，使用私钥进行签名
        signature.initSign(privateKey);

        // 传入待签名的数据
        signature.update(message.getBytes(StandardCharsets.UTF_8));

        // 执行签名操作，返回签名后的字节数组
        byte[] digitalSignature = signature.sign();

        System.out.println("Digital Signature (in Hex): " + bytesToHex(digitalSignature));

        // 验签操作放在这里
        boolean isVerified = verifySignature(message, digitalSignature, publicKey);
        System.out.println("Signature Verification: " + (isVerified ? "Valid" : "Invalid"));
    }

    // 验签方法（签名验证）
    public static boolean verifySignature(String message, byte[] signatureBytes, PublicKey publicKey) throws Exception {
        // 初始化 Signature 对象用于验证
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        // 传入原始消息数据
        signature.update(message.getBytes(StandardCharsets.UTF_8));

        // 验证签名
        return signature.verify(signatureBytes);
    }

    // 将字节数组转换为16进制字符串用于显示
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

