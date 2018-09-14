package designPattern.abstractFactory;

public class Factory4A implements produce{

	@Override
	public Food getFood() {
		return new FoodA();
	}

}
