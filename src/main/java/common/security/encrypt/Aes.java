package common.security.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import common.security.BytesStringUtil;

//对称加密，只有一个密码
public class Aes {
	/**
	  * 签名算法
	*/
	public static final String SIGN_ALGORITHMS = "SHA1PRNG";
	/**
	* 密钥算法
	*/
	public static final String KEY_ALGORITHM = "AES";
	/**
	* 加密算法   
	*	 加密解密算法/工作模式/填充方式
	*/
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	/*
	 * 1.构造密钥生成器 2.根据password规则初始化密钥生成器 3.产生密钥 4.创建和初始化密码器 5.内容加密 6.返回字符串
	 */
	public static String AESencode(String password, String content) {

		// 1.构造密钥生成器，指定为AES算法,不区分大小写
		try {
			KeyGenerator keygen = KeyGenerator.getInstance(KEY_ALGORITHM);
			// 2.根据ecnodeRules规则初始化密钥生成器
			// 生成一个128位的随机源,根据传入的字节数组
			// SecureRandom 实现完全随操作系统本身的內部状态，除非调用方在调用 getInstance 方法之后又调用了 setSeed 方法；
			//该实现在 windows 上每次生成的 key 都相同，但是在 solaris 或部分 linux 系统上则不同。
			SecureRandom random = SecureRandom.getInstance(SIGN_ALGORITHMS);
			random.setSeed(password.getBytes());
			keygen.init(128, random);
			// 3.产生原始对称密钥
			SecretKey original_key = keygen.generateKey();
			// 4.获得原始对称密钥的字节数组
			byte[] raw = original_key.getEncoded();
			// 5.根据字节数组生成AES密钥
			SecretKey key = new SecretKeySpec(raw, "AES");
			// 6.根据指定算法AES自成密码器
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			// 7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
			cipher.init(Cipher.ENCRYPT_MODE, key);
			// 8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
			byte[] byte_encode = content.getBytes("utf-8");
			System.out.println("加密前16进制字符串:"+ BytesStringUtil.bytesToHexString(byte_encode) +"\n加密前base64: "+Base64.encodeBase64String(byte_encode));
			// 9.根据密码器的初始化方式--加密：将数据加密
			byte[] byte_AES = cipher.doFinal(byte_encode);
			// 10.将加密后的数据转换为十六进制字符串
			String result = BytesStringUtil.bytesToHexString(byte_AES);
			String result2 = Base64.encodeBase64String(byte_AES);
			System.out.println("加密后16进制字符串:" + result+"\n加密后base64编码:"+result2);
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 解密 解密过程： 1.同加密1-4步 2.将加密后的字符串反纺成byte[]数组 3.将加密内容解密
	 */
	@Test
	public static String AESdecode(String password, String content) {
		try {
			// 1.构造密钥生成器，指定为AES算法,不区分大小写
			KeyGenerator keygen = KeyGenerator.getInstance(KEY_ALGORITHM);
			// 2.根据ecnodeRules规则初始化密钥生成器
			// 生成一个128位的随机源,根据传入的字节数组
			SecureRandom random = SecureRandom.getInstance(SIGN_ALGORITHMS);
			random.setSeed(password.getBytes());
			keygen.init(128, random);
			// 3.产生原始对称密钥
			SecretKey original_key = keygen.generateKey();
			// 4.获得原始对称密钥的字节数组
			byte[] raw = original_key.getEncoded();
			// 5.根据字节数组生成AES密钥
			SecretKey key = new SecretKeySpec(raw, "AES");
			// 6.根据指定算法AES自成密码器
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			// 7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
			cipher.init(Cipher.DECRYPT_MODE, key);
			// 8.将加密并编码后的内容解码成字节数组
			/*
			 * javax.crypto.IllegalBlockSizeException: Input length must be multiple of 16
			 * when decrypting with padded cipher 原因
			 * :加密后的byte数组是不能强制转换成字符串的，换言之：字符串和byte数组在这种情况下不是互逆的；要避免这种情况，我们需要做一些修订，
			 * 可以考虑将二进制数据转换成十六进制表示
			 */
			byte[] byte_content = BytesStringUtil.hexStringToBytes(content);
			/*
			 * 解密
			 */
			byte[] afterDecrypt = cipher.doFinal(byte_content);
			String result = new String(afterDecrypt, "utf-8");
			String result2 = Base64.encodeBase64String(afterDecrypt);
			System.out.println("解密后: "+result);
			System.out.println("解密后16进制字符串:"+ BytesStringUtil.bytesToHexString(afterDecrypt)   + "\nbase64解码:"+ result2);
			
			
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		String content = "test郑程";
		String password = "12345678";
		String afterEncrypt = Aes.AESencode(password, content);
		Aes.AESdecode(password, afterEncrypt);
	}

}
