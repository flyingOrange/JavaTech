package common.security;

import org.junit.Test;

import common.security.BASE64;
import common.security.MD5;

public class TestSecurity {
	
	@Test
	public void testMD5(){
		
		String input = "12345";
		String result = MD5.encrypt(input.getBytes());
		System.out.println("result="+result);
		
	}
	
	//@Test
	public void testBase64(){
		String str = "123456789";
		try {
			String result1 = BASE64.encryptBASE64(str.getBytes());
			System.out.println("result1=====加密数据==========   " + result1);

			byte result2[] = BASE64.decryptBASE64(result1);
			String str2 = new String(result2);
			System.out.println("str2========解密数据========   " + str2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


}
