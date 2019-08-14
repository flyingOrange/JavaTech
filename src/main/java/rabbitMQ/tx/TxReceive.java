package rabbitMQ.tx;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import rabbitMQ.util.ConnectionUtil;

public class TxReceive {
    private static String QUEUE_NAME = "queue_tx";
    
    public static void main(String[] args) throws IOException, TimeoutException{
        Connection connection = ConnectionUtil.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        
        channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
                    throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("receive:"+msg);
            }
            
        });
        
      
    }
    

}
