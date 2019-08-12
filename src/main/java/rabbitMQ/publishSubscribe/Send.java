package rabbitMQ.publishSubscribe;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import rabbitMQ.util.ConnectionUtil;

public class Send {
	private static String EXCHANGE_NAME = "test_exchange";
	
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		Connection connection = ConnectionUtil.getConnection();
		//获取通道
		Channel channel = connection.createChannel();
		//声明交换机
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");//分发类型
		
		
		String msg = "ffffffff";
        channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
        System.out.println("send:"+ msg);

		
		channel.close();
		connection.close();
	}
}
