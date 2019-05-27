package designPattern.builder;

public class Main {

	public static void main(String[] args) {
		Builder builder = new ConcreteBuilder();
		Director director = new Director(builder);
		
		//指挥者装机
		director.construct();
		//装机完成
		Computer computer = builder.getComputer();
		computer.Show();
	}

}
