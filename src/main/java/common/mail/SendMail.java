package common.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
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
	 * 通过javamail 用qq邮件发送,qq邮箱必须先设置开启pop3/smtp协议
	 */
	 @Test
	public void sendMailByJavaMailThroughQQ(String receiver,String deadline) throws GeneralSecurityException,
			AddressException, MessagingException {
		Properties prop = new Properties();
		// 开启debug调试，以便在控制台查看
		//prop.setProperty("mail.debug", "true");
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
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
		// 邮件的标题
		message.setSubject("服务器更换消息");
		// 邮件的文本内容
		String notify = "您好，今天上午服务器23.236.75.138被封杀，尚不知道未来能否恢复，现增加一条路线，目前三条可用路线为:(207.148.107.212)、(45.62.98.131)、(104.129.182.2)，请大家择优使用";
		//String content = "<h1>请续费充值</h1><br/><img src='http://m.qpic.cn/psb?/V10A4nPr3gJrJj/xwmnaxfluBsPN8yb8D7iff4wvShPPHZH3lCEhD9gXyg!/b/dHIAAAAAAAAA&bo=OATKBXgG4AgDCUk!&rf=viewer_4' />"
		//+ "<img src='http://m.qpic.cn/psb?/V10A4nPr3gJrJj/jW.PW0d.i1bv1RMr2jILj.TRGvBLXeyiwbPNHhYv67o!/b/dFcBAAAAAAAA&bo=OARmBqAFiAgRGVc!&rf=viewer_4' />";
		String content = "<h1>如想继续使用,请续费充值</h1><br/><img src='http://m.qpic.cn/psb?/V10A4nPr3gJrJj/xwmnaxfluBsPN8yb8D7iff4wvShPPHZH3lCEhD9gXyg!/b/dHIAAAAAAAAA&bo=OATKBXgG4AgDCUk!&rf=viewer_4' />";
		
		message.setContent(notify, "text/html;charset=UTF-8");
		// 发送邮件
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
	}

	/*
	 * 使用javamail 发送带有附件的邮件
	 */
	// @Test
	public void sendMailByJavaMailWithAppendix() throws AddressException,
			MessagingException, IOException {
		// 环境
		Session session = Session.getDefaultInstance(new Properties());
		// 邮件
		MimeMessage msg = new MimeMessage(session);
		// 设置主题
		msg.setSubject("test123456");
		// 发件人，注意中文的处理
		msg.setFrom(new InternetAddress("zcoranges@163.com"));
		// 设置邮件回复人
		// msg.setReplyTo(new Address[] { new InternetAddress("123456@163.com")
		// });
		// 设置发送方式与接收者
		msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(
				"370686124@qq.com"));
		// 整封邮件的MINE消息体
		MimeMultipart msgMultipart = new MimeMultipart("mixed");// 混合的组合关系
		// 设置邮件的MINE消息体
		msg.setContent(msgMultipart);

		// 附件1
		MimeBodyPart attch1 = new MimeBodyPart();
		// 附件2
		MimeBodyPart attch2 = new MimeBodyPart();
		// 正文内容
		MimeBodyPart content = new MimeBodyPart();
		// 把内容，附件1，附件2加入到 MINE消息体中
		msgMultipart.addBodyPart(attch1);
		msgMultipart.addBodyPart(attch2);
		msgMultipart.addBodyPart(content);

		// 把文件，添加到附件1中
		// 数据源
		DataSource ds1 = new FileDataSource(new File(
				"‪C:/Users/orange/Desktop/code_16852.zip"));
		// 数据处理器
		DataHandler dh1 = new DataHandler(ds1);
		// 设置第一个附件的数据
		attch1.setDataHandler(dh1);
		// 设置第一个附件的文件名
		attch1.setFileName("code_16852.zip");

		// 把文件，添加到附件2中
		DataSource ds2 = new FileDataSource(new File(
				"‪C:/Users/orange/Desktop/20180106193158.png"));
		DataHandler dh2 = new DataHandler(ds2);
		attch2.setDataHandler(dh2);
		attch2.setFileName("20180106193158.png");

		// 正文（图片和文字部分）
		MimeMultipart bodyMultipart = new MimeMultipart("related");
		// 设置内容为正文
		content.setContent(bodyMultipart);

		// html代码部分
		MimeBodyPart htmlPart = new MimeBodyPart();
		// html中嵌套的图片部分
		MimeBodyPart imgPart = new MimeBodyPart();
		// 正文添加图片和html代码
		bodyMultipart.addBodyPart(htmlPart);
		bodyMultipart.addBodyPart(imgPart);

		// 把文件，添加到图片中
		DataSource imgds = new FileDataSource(new File(
				"‪C:/Users/orange/Desktop/20180106193158.png"));
		DataHandler imgdh = new DataHandler(imgds);
		imgPart.setDataHandler(imgdh);
		// 说明html中的img标签的src，引用的是此图片
		imgPart.setHeader("Content-Location", "http://sunteam.cc/logo.jsg");

		// html代码
		htmlPart.setContent(
				"<span style='color:red'>中文呵呵</span><img src=\"http://sunteam.cc/logo.jsg\">",
				"text/html;charset=utf-8");
		// 生成文件邮件
		// msg.saveChanges();
		// // 输出
		// OutputStream os = new FileOutputStream(
		// "‪C:/Users/orange/Desktop/demo.eml");
		// msg.writeTo(os);
		// os.close();
		Transport.send(msg);
	}

}
