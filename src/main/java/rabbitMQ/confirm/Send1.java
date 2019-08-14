package rabbitMQ.confirm;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import rabbitMQ.util.ConnectionUtil;

//普通模式，单条，receive都相同
public class Send1 {
    private static String QUEUE_NAME = "queue_tx";
    
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException{
        Connection connection = ConnectionUtil.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //
        channel.confirmSelect();
        
        String msg = "hello confirm message";
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        
        if(!channel.waitForConfirms()) {
            System.out.println("message send failed");
        }else {
            System.out.println("message send ok");
        }
        
        
    }
}
