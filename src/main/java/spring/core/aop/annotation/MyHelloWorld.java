package spring.core.aop.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyHelloWorld {
	
	public MyHelloWorld(){}
	
	@Before(value = "execution(* spring.core.aop.xml.HelloWorld.annotation())")
	public void before(){
		
		System.out.println("MyHelloWorld  Before................");
		
	}

}
