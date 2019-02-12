package common.security.signature;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Before;
import org.junit.Test;

import common.security.encrypt.DSA;
import common.security.encrypt.RSA;

public class DSASignature {
    // 公钥
    public byte[] publicKey;
    // 私钥
    public byte[] privateKey;

    @Before
    // 初始化密钥
    public void initKey() throws Exception {
        Map<String, Object> keyMap = DSA.initKey();
        publicKey = DSA.getPublicKey(keyMap);
        privateKey = DSA.getPrivateKey(keyMap);
        System.out.println("公钥:" + Base64.encodeBase64String(publicKey));
        System.out.println("私钥:" + Base64.encodeBase64String(privateKey));
    }
    
    @Test
    //DSA签名长度和密钥长度无关,但可能与待签名数据存在某种关联
    public void sign() throws Exception {
        String inputStr = "DSA数字签名";
        byte[] data = inputStr.getBytes();
        // 产生签名
        byte[] sign = DSA.sign(data, privateKey);
        System.out.println("签名:" + Hex.encodeHexString(sign));
        // 验证签名
        boolean status = DSA.verify(data, publicKey, sign);
        System.out.println("状态:" + status);
        // 验证
        assertTrue(status);
    }
}
