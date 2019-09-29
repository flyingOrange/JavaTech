package rabbitMQ.tx;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitMQ.util.ConnectionUtil;
/*
 * 使用事务保证生产者的消息到达MQ
 * 缺点：请求次数过多，降低系统吞吐量
 * */
public class TxSend {
    private static String QUEUE_NAME = "queue_tx";
    
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String msg = "hello tx message";
        
        try {
            channel.txSelect();
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            
            //抛出异常
            //int xx = 1/0;
            
            System.out.println("send:"+msg);
            channel.txCommit();
        } catch (Exception e) {
            channel.txRollback();
            System.out.println("Rollback");
        }
        channel.close();
        connection.close();        
    }
    
}
