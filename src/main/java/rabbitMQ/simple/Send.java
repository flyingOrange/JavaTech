package rabbitMQ.simple;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import rabbitMQ.util.ConnectionUtil;

public class Send {
	private static String QUEUE_NAME = "test_simple_queue";

	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectionUtil.getConnection();
		//获取通道
		Channel channel = connection.createChannel();
		//创建队列声明
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		String msg = "hello simple q";
		channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
		System.out.println("send msg: "+ msg);
		
		channel.close();
		connection.close();
	}
}
