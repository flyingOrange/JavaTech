package rabbitMQ.routing;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import rabbitMQ.util.ConnectionUtil;

public class Receive1 {
    private static String QUEUE_NAME = "test_work_queue_email";
    private static String EXCHANGE_NAME = "test_exchange";

	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		//定义队列消费者
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		//绑定队列到交换机(有多个交换机要绑定多个)
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "error");
        
	    //保证一次只分发一个
        channel.basicQos(1);
		
		DefaultConsumer Consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String msg = new String(body,"utf-8");
				
			
				try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("[1] receive:"+msg);
                    //手动回执
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
			}
		};
		
		boolean autoAck = false;
		//监听队列
		channel.basicConsume(QUEUE_NAME, autoAck,Consumer);
		
	}
}
