package common.security;

import java.security.MessageDigest;

/*
 1、压缩性：任意长度的数据，算出的MD5值长度都是固定的。
 2、容易计算：从原数据计算出MD5值很容易。
 3、抗修改性：对原数据进行任何改动，哪怕只修改1个字节，所得到的MD5值都有很大区别。
 4、弱抗碰撞：已知原数据和其MD5值，想找到一个具有相同MD5值的数据（即伪造数据）是非常困难的。
 5、强抗碰撞：想找到两个不同的数据，使它们具有相同的MD5值，是非常困难的。
 MD5的作用是让大容量信息在用数字签名软件签署私人密钥前被”压缩”成一种保密的格式（就是把一个任意长度的字节串变换成一定长的十六进制数字串）。
 除了MD5以外，其中比较有名的还有sha-1、RIPEMD以及Haval等。

 **/

public class MD5 {
	
	public static String encrypt(byte[] input){
		StringBuffer sb = new StringBuffer(32); 
		try {
		    MessageDigest md = MessageDigest.getInstance("MD5");
		    byte[] array = md.digest(input);
		    for (int i = 0; i < array.length; i++) {  
	            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));  
	        }
		    return sb.toString();
		} catch (Exception e) {
		    throw new RuntimeException(e);
		}
	}

	public static String decrypt(){
		
		return null;
	}

}
