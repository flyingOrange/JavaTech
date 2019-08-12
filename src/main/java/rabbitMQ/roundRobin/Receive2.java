package rabbitMQ.roundRobin;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import rabbitMQ.util.ConnectionUtil;

public class Receive2 {
	private static String QUEUE_NAME = "test_work_queue";

	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		//定义队列消费者

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		DefaultConsumer Consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String msg = new String(body,"utf-8");
				System.out.println("[2] receive:"+msg);
			
				try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
			}
		};
		
		boolean autoAck = true;
		//监听队列
		channel.basicConsume(QUEUE_NAME, autoAck,Consumer);
		
	}
}
