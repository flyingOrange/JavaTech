package designPattern.factory.simpleFactory;
/*
 * 
 * 简单工厂模式又叫静态工厂模式，通过专门定义一个类来负责其它类的实例，被创建的类通常具有共同的父类
 * 
 * */
public class TestCase {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		//实例化一个Apple，用到多态，父类引用指向子类对象
		Fruit apple = new Apple();
		apple.get();
		Fruit banana = new Banana();
		banana.get();
		//使用工厂产生实例
		Fruit apple2 = FruitFactory.getApple();
		apple2.get();
		Fruit banana2 = FruitFactory.getBanana();
		banana2.get();
		
		Fruit apple3 = FruitFactory.getFruit("apple");
		apple3.get();
		Fruit banana3 = FruitFactory.getFruit("banana");
		banana3.get();
		
		Fruit apple4 = FruitFactory.getFruitBetter("designPattern.factory.simpleFactory.Apple");
		apple4.get();
		
	}
}
