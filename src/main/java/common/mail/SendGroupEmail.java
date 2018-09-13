package common.mail;

import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;

//从shadowsocks数据库user表中读取数据，向其中即将到期的用户发送提醒邮件
public class SendGroupEmail {

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		String URL = "jdbc:mysql://127.0.0.1:3306/shadowsocks?useUnicode=true&amp;characterEncoding=utf-8";
		String USER = "root";
		String PASSWORD = "000orange";
		Class.forName("com.mysql.jdbc.Driver");

		String sql = "select * from user";
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		//当前时间毫秒数
		long now = new Date().getTime();
		//邮件类
		SendMail sendMail = new SendMail();
		while (rs.next()) {
			String email = rs.getString("email");
			String deadline = rs.getString("deadline");
			if (StringUtils.isAnyBlank(email,deadline))
				continue;
			email = email.trim();
			deadline = deadline.trim();
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			long time = 0l;
			try {
				time = simpleDateFormat.parse(deadline).getTime();

			} catch (ParseException e) {
				System.out.println("time format failed");
				e.printStackTrace();
			}
			
			
			try {
				sendMail.sendMailByJavaMailThroughQQ(email, deadline);
				System.out.println("...............................................");
				System.out.println(email + " | " + deadline + " | " + time);
			} catch (GeneralSecurityException | MessagingException e) {
				System.out.println("邮件发送失败:"+email);
				e.printStackTrace();
			}
			
			//筛选出临近到期day天数的用户
/*			long dis = time - now;
			int day = 5;
			if(dis>0 && dis<=(3600000*24*day)){
				System.out.println("...............................................");
				System.out.println(email + " | " + deadline + " | " + time);
				
				try {
					sendMail.sendMailByJavaMailThroughQQ(email, deadline);
					System.out.println("...............................................");
				} catch (GeneralSecurityException | MessagingException e) {
					System.out.println("邮件发送失败:"+email);
					e.printStackTrace();
				}
			}*/
			
		}

		ps.execute();
		ps.close();
		conn.close();

	}

}
