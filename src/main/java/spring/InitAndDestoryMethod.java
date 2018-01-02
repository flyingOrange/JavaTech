package spring;
import spring.Student;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class InitAndDestoryMethod {

	public static void main(String[] args) {
		System.out.println("java.classpath:   "+System.getProperty("java.class.path"));
		
		String []locations = {"spring.xml"};
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(locations);
		//Course course = (Course) ctx.getBean("courseBean");
		Student student = (Student) ctx.getBean("studentBean");
		//student.say();
		
		ctx.registerShutdownHook();

	
	}

}
