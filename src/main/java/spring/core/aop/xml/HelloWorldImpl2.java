package spring.core.aop.xml;


public class HelloWorldImpl2 implements HelloWorld{
	public HelloWorldImpl2(){}

	@Override
	public void printHelloWorld(){
        System.out.println("Enter HelloWorldImpl2.printHelloWorld()");
    }
    
	@Override
    public void doPrint(){
        System.out.println("Enter HelloWorldImpl2.doPrint()");
    }

	@Override
	public void annotation() {
		System.out.println("Enter HelloWorldImpl2.annotation()");
	}

}
