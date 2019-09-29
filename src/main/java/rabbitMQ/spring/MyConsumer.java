package rabbitMQ.spring;

public class MyConsumer {
    
    public void listen(String msg) {
        System.out.println("消费者:"+msg);
    }

}
