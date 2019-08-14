package rabbitMQ.confirm;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import rabbitMQ.util.ConnectionUtil;

//异步confirm模式，receive都相同
public class Send3 {
    private static String QUEUE_NAME = "queue_tx";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtil.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // confirm模式
        channel.confirmSelect();

        // 未确认消息标识
        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());
        
        //通道添加监听
        channel.addConfirmListener(new ConfirmListener() {

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                if(multiple) {
                    System.out.println("handle ack multiple");
                    confirmSet.headSet(deliveryTag+1).clear();
                }else {
                    System.out.println("handle ack false");
                    confirmSet.remove(deliveryTag);
                }
                
            }

            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                if(multiple) {
                    System.out.println("handle ack multiple");
                    confirmSet.headSet(deliveryTag+1).clear();
                }else {
                    System.out.println("handle ack false");
                    confirmSet.remove(deliveryTag);
                }
            }
        });

        String msg = "hello confirm message";
        while(true) {
            long seqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            confirmSet.add(seqNo);
        }

    }
}
