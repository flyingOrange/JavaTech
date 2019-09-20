package designPattern.state.v3;

/*
 * 状态模式：当控制一个对象状态转换的条件表达式过于复杂时，把状态的判断逻辑转译到表现不同状态的一系列类中，把复杂的判断逻辑简化。
 * 
 * */
public class MainClass {
	
	public static void main(String[] args) {
		Person person = new Person();
		person.setHour(7);
		person.doSomething();
		
		person.setHour(12);
		person.doSomething();
		
		person.setHour(18);
		person.doSomething();
		
		person.setHour(8);
		person.doSomething();
		
		person.setHour(7);
		person.doSomething();
	}

}
