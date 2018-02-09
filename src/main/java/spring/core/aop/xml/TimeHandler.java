package spring.core.aop.xml;

/*用于增强的方法*/
public class TimeHandler {
	public TimeHandler() {
	}

	public void printTime() {
		System.out.println("CurrentTime = " + System.currentTimeMillis());
	}
}
