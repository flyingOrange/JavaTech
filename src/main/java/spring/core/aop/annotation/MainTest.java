package spring.core.aop.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.aop.xml.HelloWorld;

public class MainTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/core/aop/xml/aop.xml");
		HelloWorld hw1 = (HelloWorld) ctx.getBean("helloWorldImpl1");
		HelloWorld hw2 = (HelloWorld) ctx.getBean("helloWorldImpl2");
		
		hw1.annotation();
		
		System.out.println();
		
		hw2.annotation();
		
	}

}
