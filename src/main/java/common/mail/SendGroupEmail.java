package common.mail;

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
		String URL = "jdbc:mysql://172.105.199.112:3306/ss172.105.199.112?useUnicode=true&amp;characterEncoding=utf-8";
		String USER = "root";
		String PASSWORD = "000Orange...";
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

}
