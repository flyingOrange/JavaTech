package common.security;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

//非对称加密，有两个密码--公钥、私钥
public class RSA {
	/**
	 * 非对称加密密钥算法
	 */
	public static final String KEY_ALGORITHM = "RSA";
	// 公钥
	public static final String PUBLIC_KEY = "RSAPublicKey";
	// 私钥
	public static final String PRIVATE_KEY = "RSAPrivateKey";
	/**
	 * RSA密钥长度 默认1024 密钥长度必须是64的倍数 范围在512-65536之间
	 */
	public static final int KEY_SIZE = 512;

	/**
	 * 初始化密钥
	 */
	public static Map<String, Object> initKey() throws Exception {
		// 实例化密钥对生成器
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		// 初始化密钥生成器
		keyPairGenerator.initialize(KEY_SIZE);
		// 生成密钥对
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		// 公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		// 私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		// 封装密钥
		Map<String, Object> keyMap = new HashMap<String, Object>();
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	/**
	 * 取得私钥
	 */
	public static byte[] getPrivateKey(Map<String, Object> keyMap) {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return key.getEncoded();
	}

	/**
	 * 取得公钥
	 */
	public static byte[] getPublicKey(Map<String, Object> keyMap) {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return key.getEncoded();
	}

	/**
	 * 私钥加密
	 */
	public static byte[] encryptByPrivateKey(byte[] data, byte[] key) throws Exception {
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 生成私钥
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * 私钥解密
	 */
	public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception {
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 生成私钥
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * 公钥加密
	 */
	public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 生成公钥
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/**
	 * 公钥解密
	 */
	public static byte[] decryptByPublicKey(byte[] data, byte[] key) throws Exception {
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 生成公钥
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	public static void main(String[] args) throws Exception {
		// 初始化密钥
		Map<String, Object> keyMap = RSA.initKey();
		// 公钥
		byte[] publicKey = RSA.getPublicKey(keyMap);
		// 私钥
		byte[] privateKey = RSA.getPrivateKey(keyMap);
		System.out.println("公钥:"+Base64.encodeBase64String(publicKey));
		System.out.println("私钥:"+Base64.encodeBase64String(privateKey));
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\n---私钥加密---公钥解密---");
		String inputStr1 = "RSA加密算法";
		byte[] data1 = inputStr1.getBytes();
		//加密
		byte[] encodedData1 = RSA.encryptByPrivateKey(data1, privateKey);
		System.out.println("加密前:"+inputStr1+",加密后:"+Base64.encodeBase64String(encodedData1));
		//解密
		byte[] decodedData1 = RSA.decryptByPublicKey(encodedData1, publicKey);
		String outputStr1 = new String(decodedData1);
		System.out.println("解密后:"+outputStr1);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\n---公钥加密---私钥解密---");
		String inputStr2 = "测试RSA";
		byte[] data2 = inputStr2.getBytes();
		//加密
		byte[] encodedData2 = RSA.encryptByPublicKey(data2, publicKey);
		System.out.println("加密前:"+inputStr2+",加密后:"+Base64.encodeBase64String(encodedData2));
		//解密
		byte[] decodedData2 = RSA.decryptByPrivateKey(encodedData2, privateKey);
		String outputStr2 = new String(decodedData2);
		System.out.println("解密后:"+outputStr2);	
				
				
				
	}

}
