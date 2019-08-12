package rabbitMQ.util;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {
	
	static public Connection getConnection() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(5672);
		//设置vhost和用户名密码
		factory.setVirtualHost("/vhost_orange");
		factory.setUsername("orange");
		factory.setPassword("000orange");
		
		return factory.newConnection();
	}

}
