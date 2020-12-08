package com.githup.zzwloves.util;

import java.io.File;
import java.io.FileInputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA 工具类
 *
 * @author zhuzw
 * @version <b>1.0.0</b>
 */
public final class RSAUtils {

    /**
     * 算法
     */
    private static final String ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    private static final String SIGN_ALGORITHM = "SHA256WithRSA";

    /**
     * 公钥Map Key
     */
    public static final String PUBLIC_KEY = "publicKey";

    /**
     * 私钥Map Key
     */
    public static final String PRIVATE_KEY = "privateKey";


    /**
     * 加签
     *
     * @param content    签名原串
     * @param privateKey 私钥
     * @param charset    字符集
     * @return 签名
     * @throws Exception 异常
     */
    public static String rsa256Sign(String content, String privateKey, String charset) throws Exception {
        if (StringUtils.isEmptyAny(content, privateKey)) {
            return "";
        }
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        PrivateKey priKey = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initSign(priKey);
        if (StringUtils.isEmpty(charset)) {
            signature.update(content.getBytes());
        } else {
            signature.update(content.getBytes(charset));
        }
        byte[] signed = signature.sign();
        return Base64.getEncoder().encodeToString(signed);
    }


    /**
     * 验签
     *
     * @param content   验签原串
     * @param sign      签名
     * @param publicKey 公钥
     * @param charset   字符集
     * @return 验签结果
     * @throws Exception 异常
     */
    public static boolean rsa256VerifySign(String content, String sign, String publicKey, String charset) throws Exception {
        if (StringUtils.isEmptyAny(content, sign, publicKey)) {
            return false;
        }

        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initVerify(pubKey);
        if (StringUtils.isNotEmpty(charset)) {
            signature.update(content.getBytes(charset));
        } else {
            signature.update(content.getBytes());
        }
        return signature.verify(Base64.getDecoder().decode(sign.getBytes()));
    }

    /**
     * 通过PFX证书获取RSA公钥和私钥
     *
     * @param pfxPath  证书存放路径
     * @param password 证书密码
     * @return MAP
     * @throws Exception 异常
     */
    public static Map<String, String> loadKeyByFile(String pfxPath, String password) throws Exception {
        if (StringUtils.isEmptyAny(pfxPath, password)) {
            return new HashMap<>(0);
        }

        return loadKeyByFile(new FileInputStream(pfxPath), password);
    }

    /**
     * 通过PFX证书获取RSA公钥和私钥
     *
     * @param pfx      证书
     * @param password 证书密码
     * @return map对象
     * @throws Exception 异常
     */
    public static Map<String, String> loadKeyByFile(File pfx, String password) throws Exception {
        if (pfx == null || StringUtils.isEmpty(password)) {
            return new HashMap<>(0);
        }
        return loadKeyByFile(new FileInputStream(pfx), password);
    }

    private static Map<String, String> loadKeyByFile(FileInputStream fis, String password) throws Exception {
        char[] nPassword;
        if (StringUtils.isEmpty(password)) {
            nPassword = null;
        } else {
            nPassword = password.toCharArray();
        }
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(fis, nPassword);
        fis.close();
        Enumeration enumas = ks.aliases();
        String keyAlias = null;
        if (enumas.hasMoreElements()) {
            keyAlias = (String) enumas.nextElement();
        }
        PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
        Certificate cert = ks.getCertificate(keyAlias);
        PublicKey pubkey = cert.getPublicKey();
        String publicKey = Base64.getEncoder().encodeToString(pubkey.getEncoded());
        String privateKey = Base64.getEncoder().encodeToString(prikey.getEncoded());
        Map<String, String> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

}
