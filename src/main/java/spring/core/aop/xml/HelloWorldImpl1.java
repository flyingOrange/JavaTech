package spring.core.aop.xml;

public class HelloWorldImpl1 implements HelloWorld{
	
	public HelloWorldImpl1(){}

	@Override
	public void printHelloWorld() {
		System.out.println("Enter HelloWorldImpl1.printHelloWorld()");
	}

	@Override
	public void doPrint() {
		System.out.println("Enter HelloWorldImpl1.doPrint()");
	}

	@Override
	public void annotation() {
		System.out.println("Enter HelloWorldImpl1.annotation()");
	}

}
