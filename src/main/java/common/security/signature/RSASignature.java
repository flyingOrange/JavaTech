package common.security.signature;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Before;
import org.junit.Test;

import common.security.encrypt.RSA;

/*
 * RSA经典数字签名算法
 * */
public class RSASignature {
    // 公钥
    public byte[] publicKey;
    // 私钥
    public byte[] privateKey;

    @Before
    // 初始化密钥
    public void initKey() throws Exception {
        Map<String, Object> keyMap = RSA.initKey();
        publicKey = RSA.getPublicKey(keyMap);
        privateKey = RSA.getPrivateKey(keyMap);
        System.out.println("公钥:" + Base64.encodeBase64String(publicKey));
        System.out.println("私钥:" + Base64.encodeBase64String(privateKey));
    }

    @Test
    //RSA签名长度和公钥相等
    public void sign() throws Exception {
        String inputStr = "RSA数字签名";
        byte[] data = inputStr.getBytes();
        // 产生签名
        byte[] sign = RSA.sign(data, privateKey);
        System.out.println("签名:" + Hex.encodeHexString(sign));
        // 验证签名
        boolean status = RSA.verify(data, publicKey, sign);
        System.out.println("状态:" + status);
        // 验证
        assertTrue(status);
    }

}
