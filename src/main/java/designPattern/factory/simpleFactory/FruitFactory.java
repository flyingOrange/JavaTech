package designPattern.factory.simpleFactory;

public class FruitFactory {

	public static Fruit getApple() {
		return new Apple();
	}

	public static Fruit getBanana() {
		return new Banana();
	}

	public static Fruit getFruit(String type) {
		if ("apple".equalsIgnoreCase(type)) {
			return new Apple();
		} else if ("banana".equalsIgnoreCase(type)) {
			return new Banana();
		} else {
			System.out.println("没有匹配的实例对象");
			return null;
		}
	}

	public static Fruit getFruitBetter(String type)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class fruit = Class.forName(type);
		return (Fruit) fruit.newInstance();
	}

}
