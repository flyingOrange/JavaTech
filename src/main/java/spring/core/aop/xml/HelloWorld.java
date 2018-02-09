package spring.core.aop.xml;
/*
 * 被增强的接口
 */
public interface HelloWorld {
	
	void printHelloWorld();

	void doPrint();
	
	//专门测试注解方式
	void annotation();
}
