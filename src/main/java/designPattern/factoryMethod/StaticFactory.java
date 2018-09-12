package designPattern.factoryMethod;

//常用的工厂模式是静态工厂，利用static方法，作为一种类似于常见的工具类Utils等辅助效果
public class StaticFactory {
	private StaticFactory(){}
	
	public static food getF1() {
		return new F1();
	}
	public static food getF2() {
		return new F2();
	}
	public static food getF3() {
		return new F3();
	}
	
}
