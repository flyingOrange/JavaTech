package test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  
import java.security.SecureRandom;  
import java.security.Security;  
import javax.crypto.Cipher;  
import javax.crypto.KeyGenerator;  
import javax.crypto.SecretKey;  
import javax.crypto.spec.SecretKeySpec;  

public class TestCommon {

	public static void main(String[] args) throws Exception {
//		//字符串转化为Date
//		String date1 = "2011-03-06 12:22:43";
//		SimpleDateFormat st1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//	    Date date = st1.parse(date1);  
//	    System.out.println(date);
//	    
//		//获取当前时间的字符串
//	    Date date2 = new Date();
//	    SimpleDateFormat st2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String str2 = st2.format(date2);
//		System.out.println(str2);
//		//System.out.println(date2.before(date1));
		
		//数组拷贝
//		String []objs1 = {"1","2","3","4","5","6"};
//		System.out.println(objs1.length);
//		String []objs2 = Arrays.copyOf(objs1, 10);
//		System.out.println(objs2[0]);

		//遍历hashMap
//		HashMap<String, String> hm = new HashMap<String, String>();
//		hm.put("key1", "orange");
//		hm.put("key2", "hello");
//		hm.put("key3", "wind");
//		hm.put("key4", "tommy");
//		hm.put("key5", "gaga");
//		for(Entry<String, String> entry : hm.entrySet())
//			System.out.println(entry.getKey()+"   "+entry.getValue());
//		for(String key : hm.keySet())
//			System.out.println(key);
		
		
//		String s1 = "ha";
//		String s2 = new String("ha");
//		
//		System.out.println(s1.equals(s2));
//		System.out.println(s1.hashCode()+"  "+s2.hashCode());
//		System.out.println(s1 == s2);
//		int a[] = {2,9,4,6,3,8,1,7,5};
		
		
		
		
		
	}
	/**将二进制转换成16进制 
	 * @param buf 
	 * @return 
	 */  
	public static String parseByte2HexStr(byte buf[]) 
	{  
	        StringBuffer sb = new StringBuffer();  
	        for (int i = 0; i < buf.length; i++) {  
	                String hex = Integer.toHexString(buf[i] & 0xFF);  
	                if (hex.length() == 1) {  
	                        hex = '0' + hex;  
	                }  
	                sb.append(hex.toUpperCase());  
	        }  
	        return sb.toString();  
	}
	
	
	
	
	

}


