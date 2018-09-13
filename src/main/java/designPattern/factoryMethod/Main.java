package designPattern.factoryMethod;

public class Main {

	public static void main(String[] args) {

		food food = null;
		switch(3) {
		case 1:
			food = StaticFactory.getF1();
			break;
		case 2:
			food = StaticFactory.getF2();
			break;
		case 3:
			food = StaticFactory.getF3();
			break;
		}
		System.out.println(food.foodName());
		
	}

}
