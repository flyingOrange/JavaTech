package net.http.httpinvoker.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.http.httpinvoker.server.bean.User;
import net.http.httpinvoker.server.service.UserService;

public class HttpInvokerClient {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
		"classpath:/spring/core/httpinvoker/client/client-context.xml");
		
		UserService service = (UserService) ac.getBean("userService");
		User u = service.getUserbyId(2222);
		System.out.println(u);

	}
}
