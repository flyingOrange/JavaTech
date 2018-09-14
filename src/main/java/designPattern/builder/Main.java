package designPattern.builder;

public class Main {

	public static void main(String[] args) {
		Director director = new Director();
		Builder builder = new ConcreteBuilder();
		
		//指挥者装机
		director.construct(builder);
		//装机完成
		Computer computer = builder.getComputer();
		computer.Show();
	}

}
