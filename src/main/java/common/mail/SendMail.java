package common.mail;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.sun.mail.util.MailSSLSocketFactory;

import org.junit.Test;

public class SendMail {

	/*
	 * 通过spring发送
	 */
	// @Test
	public void sendMailBySpring() throws MessagingException,
			UnsupportedEncodingException {
		String HOST = "smtp.163.com";
		Integer PORT = 25;
		String USERNAME = "zcoranges@163.com";
		String PASSWORD = "000orange";

		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(HOST);
		sender.setPort(PORT);
		sender.setUsername(USERNAME);
		sender.setPassword(PASSWORD);
		sender.setDefaultEncoding("Utf-8");

		Properties props = new Properties();
		props.setProperty("mail.smtp.timeout", "2000");
		props.setProperty("mail.smtp.auth", "true");
		sender.setJavaMailProperties(props);

		MimeMessage mimeMessage = sender.createMimeMessage();
		// 设置utf-8或GBK编码，否则邮件会有乱码
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
				true, "UTF-8");
		// 设置发送人和又叫标题
		messageHelper.setFrom(USERNAME, "邮件标题orange");
		// 收件人
		messageHelper.setTo("370686124@qq.com");
		messageHelper.setSubject("spring mail主题");
		messageHelper.setText("spring send mail", true);
		sender.send(mimeMessage);

	}

	/*
	 * 通过javamail发送
	 */
	// @Test
	public void sendMailByJavaMail() throws AddressException,
			MessagingException {
		// 1.创建一个程序与邮件服务器会话对象 Session
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "SMTP");
		props.setProperty("mail.smtp.host", "smtp.163.com");
		props.setProperty("mail.smtp.port", "25");
		// 指定验证为true
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.timeout", "2000");
		// 验证账号及密码，密码需要是第三方授权码
		Authenticator auth = new Authenticator() {

			public PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("zcoranges@163.com",
						"000orange");
			}
		};
		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);
		// 设置发送者
		message.setFrom(new InternetAddress("zcoranges@163.com"));
		// 设置发送方式与接收者
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(
				"370686124@qq.com"));
		// 设置主题
		message.setSubject("邮件发送测试");
		// 设置内容
		message.setContent("haha你好", "text/html;charset=utf-8");

		// 3.创建 Transport用于将邮件发送
		Transport.send(message);

	}

	/*
	 * 通过javamail 发送qq邮件 qq邮箱必须先设置开启pop3/smtp协议
	 */
	@Test
	public void sendMailByJavaMailThroughQQ() throws GeneralSecurityException,
			AddressException, MessagingException {
		Properties prop = new Properties();
		// 开启debug调试，以便在控制台查看
		prop.setProperty("mail.debug", "true");
		// 设置邮件服务器主机名
		prop.setProperty("mail.host", "smtp.qq.com");
		// 发送服务器需要身份验证
		prop.setProperty("mail.smtp.auth", "true");
		// 发送邮件协议名称
		prop.setProperty("mail.transport.protocol", "smtp");

		// 开启SSL加密，否则会失败
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.socketFactory", sf);

		// 创建session
		Session session = Session.getInstance(prop);
		// 通过session得到transport对象
		Transport ts = session.getTransport();
		// 连接邮件服务器：邮箱类型，帐号，授权码代替密码（更安全）
		ts.connect("smtp.qq.com", "1846842602", "lcggkfhcxorwejde");// 后面的字符是授权码，用qq密码反正我是失败了（用自己的，别用我的，这个号是我瞎编的，为了。。。。）
		// 创建邮件对象
		Message message = new MimeMessage(session);
		// 指明邮件的发件人
		message.setFrom(new InternetAddress("1846842602@qq.com"));
		// 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(
				"370686124@qq.com"));
		// 邮件的标题
		message.setSubject("QQ JavaMail测试");
		// 邮件的文本内容
		String content = "<h1>请续费充值</h1><br/><img src='http://www.w3school.com.cn/i/eg_tulip.jpg' />";
		message.setContent(content, "text/html;charset=UTF-8");
		// 发送邮件
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();

	}

}
