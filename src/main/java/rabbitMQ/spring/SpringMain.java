package rabbitMQ.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

    public static void main(String[] args) throws InterruptedException {
        AbstractApplicationContext act = new ClassPathXmlApplicationContext("classpath:context.xml");
        
        RabbitTemplate template = act.getBean(RabbitTemplate.class);

        //发送消息
        template.convertAndSend("hello and worlds");
        Thread.sleep(1000);
        act.destroy();
        
    }

}
