package test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSomething {
	
	@Test
    public void sha224() throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-224"); 
		String input = "309051738@qq.com";
		
		input = input.replace('@', '/');
		System.out.println(input);
		 
        // digest() method is called 
        // to calculate message digest of the input string 
        // returned as array of byte 
        byte[] messageDigest = md.digest(input.getBytes()); 

        // Convert byte array into signum representation 
        BigInteger no = new BigInteger(1, messageDigest); 

        // Convert message digest into hex value 
        String hashtext = no.toString(16); 

        // Add preceding 0s to make it 32 bit 
        while (hashtext.length() < 32) { 
            hashtext = "0" + hashtext; 
        } 

        // return the HashText 
        System.out.println(hashtext);
	}
	

    //@Test
    public void readFileNames() {
        // 通过LoggerFactory获取Logger实例
        final Logger logger = LoggerFactory.getLogger(TestSomething.class);
        logger.info("testlog: {}", "test"); 

        logger.debug("testlog: {}", "test");

        logger.error("testlog: {}", "test");

        logger.trace("testlog: {}", "test");

        logger.warn("testlog: {}", "test");

    }

}
