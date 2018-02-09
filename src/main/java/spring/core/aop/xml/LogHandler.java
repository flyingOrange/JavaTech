package spring.core.aop.xml;

public class LogHandler {
	public LogHandler(){}
	
	public void LogBefore() {
		System.out.println("Log before method");
	}

	public void LogAfter() {
		System.out.println("Log after method");
	}
}
