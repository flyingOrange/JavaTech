package common.mail;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

//从shadowsocks数据库user表中读取数据，向其中即将到期的用户发送提醒邮件
public class SendGroupEmail {

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, InterruptedException {
		String URL = "jdbc:mysql://localhost:3306/clouddb01?useUnicode=true&amp;characterEncoding=utf-8";
		String USER = "root";
		String PASSWORD = "000orange";
		Class.forName("com.mysql.jdbc.Driver");

		String sql = "select * from account";
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		
		Set<String> emails = new HashSet<String>();
		//邮件类
		SendMail sendMail = new SendMail();
		while (rs.next()) {
			String port = rs.getString("port");
			String email = rs.getString("password");
			if (!email.contains("@"))
				continue;
			emails.add(email);
			
			//把邮箱中的@替换为/
			email = email.replace('@', '/');
			String password = sha224(email);
			String addSql = "insert ignore into users(username,password)values(\"" + email +"\",\"" + password +"\")";
			PreparedStatement newPS = conn.prepareStatement(addSql);
			newPS.executeUpdate(addSql);
			
//			newPS.setString(1, email);
//			newPS.setString(2, password);
//			newPS.executeUpdate(addSql);
			
			
//			try {
//				//sendMail.sendMailByJavaMailThroughQQ(email);
//				sendMail.sendMailByJavaMail(email);
//				System.out.println("...............................................");
//				//System.out.println(email+"  发送成功");
//			} catch (MessagingException e) {
//				System.out.println("邮件发送失败:"+email);
//				e.printStackTrace();
//			}
//			Thread.sleep(1300);
			
		}
		System.out.println(emails.size());
		System.out.println(emails);
		
		StringBuilder str = new StringBuilder();
		emails.forEach(mail->{
		    str.append(mail).append(";");
		});
		System.out.println(str);
		
		ps.execute();
		ps.close();
		conn.close();

	}
	
	public static String sha224(String input) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-224");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//String input = "309051738@qq.com";
		 
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

        System.out.println(hashtext);
        return hashtext;
	}

}
