package designPattern.builder;

/*
 * 应用场景:
 * 创建一个复合对象，被创建对象是一个具有复合属性的复合对象
 * 
 * */
public class Main {

	public static void main(String[] args) {
	    //Builder也可以是接口
		Builder builder = new ConcreteBuilder();
		Director director = new Director();
		
		//指挥者装机
		director.construct(builder);
		//装机完成
		Computer computer = builder.getComputer();
		computer.Show();
	}

}
