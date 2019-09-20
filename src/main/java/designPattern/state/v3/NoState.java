package designPattern.state.v3;

public class NoState extends State {
	@Override
	public void doSomething(Person person) {
		System.out.println("未定义");
	}

}
