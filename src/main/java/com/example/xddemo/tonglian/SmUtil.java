package com.example.xddemo.tonglian;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.jcajce.spec.SM2ParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.TreeMap;

public class SmUtil {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }


    /**
     * 算法常量:SM3withSM2
     */
    public static final String ALGORITHM_SM3SM2_BCPROV = "SM3withSM2";
    private final static int SM3withSM2_RS_LEN = 32;

    public static void main(String[] args) throws Exception {



        String ss="ce6EAOj4rhoBMJM5MJCNG4qQ/CVMTWkoRuSGpSzRAnD3U3V5QyHkQUEej2eZXRaa+qSbw2/IJJSPV0sPuAia1+ccb7OnvxyZqkV9wQyimX6qAMz0K+UWFhQ5McCcQ/XsFhhezoVd5QgL7PtdvuK1AtjuzA3J9yzNmwuPssPnKnc=";

        System.out.println(ss.length());

        String curveName = "sm2p256v1";

        // 初始化 KeyPairGenerator
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");
        keyPairGenerator.initialize(new ECGenParameterSpec(curveName), new SecureRandom());

        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // 输出密钥（Base64 编码）
        System.out.println("=== SM2 Key Pair ===");
        System.out.println("Private Key (PKCS#8, Base64):");
        System.out.println(Base64.encodeBase64String(privateKey.getEncoded()));
        System.out.println();
        System.out.println("Public Key (X.509, Base64):");
        System.out.println(Base64.encodeBase64String(publicKey.getEncoded()));

        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", "00000051");
        params.put("cusid", "990581007426001");
        params.put("randomstr", "75016315");
        params.put("signtype", "SM2");
        params.put("trxid", "112094120001088317");
        params.put("version", "11");

        String s = SybUtil.unionSign(params, Base64.encodeBase64String(privateKey.getEncoded()), "SM2");
        System.out.println(s);


        params.put("sign11111", s);


        boolean b = SybUtil.validSign(params, Base64.encodeBase64String(publicKey.getEncoded()), "SM2");
        System.out.println("验签结果111:" + b);


    }

    private static void test1() throws Exception {
        System.out.println("======= 所有已加载的安全提供者（Providers） =======");
        for (Provider provider : Security.getProviders()) {
            System.out.println("Provider: " + provider.getName());
        }

        // 1. 使用 secp256r1 生成密钥对
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        keyGen.initialize(new ECGenParameterSpec("secp256r1"));
        KeyPair keyPair = keyGen.generateKeyPair();


        // 2. 获取编码后的字节
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();

        // 3. 编码成 Base64 格式
        String privateKeyBase64 = Base64.encodeBase64String(privateKeyBytes);
        String publicKeyBase64 = Base64.encodeBase64String(publicKeyBytes);


        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", "00000051");
        params.put("cusid", "990581007426001");
        params.put("randomstr", "75016315");
        params.put("signtype", "SM2");
        params.put("trxid", "112094120001088317");
        params.put("version", "11");

        String s = SybUtil.unionSign(params, privateKeyBase64, "SM2");
        System.out.println(s);


        params.put("sign", s);


        boolean b = SybUtil.validSign(params, publicKeyBase64, "SM2");
        System.out.println("验签结果:" + b);
    }

    /**
     * 签名并BASE64编码-SM3WithSM2
     */
    public static String signSM3SM2RetBase64(final PrivateKey privateKey, String certid, final byte[] data) throws Exception {
        return Base64.encodeBase64String(signSM3SM2(privateKey, certid, data));
    }

    /**
     * 签名-SM3WithSM2
     */
    public static byte[] signSM3SM2(final PrivateKey privateKey, String certid, final byte[] data) throws Exception {
        SM2ParameterSpec parameterSpec = new SM2ParameterSpec(certid.getBytes());
        Signature signer = Signature.getInstance(ALGORITHM_SM3SM2_BCPROV, "BC");
        signer.setParameter(parameterSpec);
        signer.initSign(privateKey, new SecureRandom());
        signer.update(data);
        return byteAsn12BytePlain(signer.sign());
    }

    /**
     * 验证签名-SM3WithSM2
     */
    public static boolean verifySM3SM2(final PublicKey publicKey, String certid, final byte[] signData, final byte[] srcData) throws Exception {
        SM2ParameterSpec parameterSpec = new SM2ParameterSpec(certid.getBytes());
        Signature verifier = Signature.getInstance(ALGORITHM_SM3SM2_BCPROV, "BC");
        verifier.setParameter(parameterSpec);
        verifier.initVerify(publicKey);
        verifier.update(srcData);
        return verifier.verify(bytePlain2ByteAsn1(signData));
    }

    /**
     * 从字符串读取私钥-目前支持PKCS8(keystr为BASE64格式)
     */
    public static PrivateKey privKeySM2FromBase64Str(String keystr) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(keystr)));
    }

    /**
     * 从字符串读取RSA公钥(keystr为BASE64格式)
     */
    public static PublicKey pubKeySM2FromBase64Str(String keystr) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        return keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(keystr)));
    }

    /**
     * 将普通字节数组转换为ASN1字节数组 适用于SM3withSM2验签时验签明文转换
     */
    private static byte[] bytePlain2ByteAsn1(byte[] data) {
        if (data.length != SM3withSM2_RS_LEN * 2) throw new RuntimeException("err data. ");
        BigInteger r = new BigInteger(1, Arrays.copyOfRange(data, 0, SM3withSM2_RS_LEN));
        BigInteger s = new BigInteger(1, Arrays.copyOfRange(data, SM3withSM2_RS_LEN, SM3withSM2_RS_LEN * 2));
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(r));
        v.add(new ASN1Integer(s));
        try {
            return new DERSequence(v).getEncoded("DER");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将ASN1字节数组转换为普通字节数组 适用于SM3withSM2签名时签名结果转换
     */
    private static byte[] byteAsn12BytePlain(byte[] dataAsn1) {
        ASN1Sequence seq = ASN1Sequence.getInstance(dataAsn1);
        byte[] r = bigIntToFixexLengthBytes(ASN1Integer.getInstance(seq.getObjectAt(0)).getValue());
        byte[] s = bigIntToFixexLengthBytes(ASN1Integer.getInstance(seq.getObjectAt(1)).getValue());
        byte[] result = new byte[SM3withSM2_RS_LEN * 2];
        System.arraycopy(r, 0, result, 0, r.length);
        System.arraycopy(s, 0, result, SM3withSM2_RS_LEN, s.length);
        return result;
    }

    private static byte[] bigIntToFixexLengthBytes(BigInteger rOrS) {
        byte[] rs = rOrS.toByteArray();
        if (rs.length == SM3withSM2_RS_LEN) return rs;
        else if (rs.length == SM3withSM2_RS_LEN + 1 && rs[0] == 0)
            return Arrays.copyOfRange(rs, 1, SM3withSM2_RS_LEN + 1);
        else if (rs.length < SM3withSM2_RS_LEN) {
            byte[] result = new byte[SM3withSM2_RS_LEN];
            Arrays.fill(result, (byte) 0);
            System.arraycopy(rs, 0, result, SM3withSM2_RS_LEN - rs.length, rs.length);
            return result;
        } else {
            throw new RuntimeException("err rs: " + Hex.toHexString(rs));
        }
    }

}
