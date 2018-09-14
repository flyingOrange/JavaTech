package designPattern.abstractFactory;

public class Factory4B implements produce{

	@Override
	public Food getFood() {
		return new FoodB();
	}

}
