package designPattern.template;
/*
 * 模版方法模式:
 * 把具有特定步骤的算法中某些必要的处理委让给抽象方法，通过子类继承对抽象方法的不同实现改变整个算法的行为
 * */
public class MainClass {
	public static void main(String[] args) {
		MakeCar makeBus = new MakeBus();
//		makeBus.makeHead();
//		makeBus.makeBody();
//		makeBus.makeTail();
		makeBus.make();
		
		MakeCar makeJeep = new MakeJeep();
		makeJeep.make();
		
		
	}

}
