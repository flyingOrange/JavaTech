package designPattern.factory.factoryMethod;

public class F2Factory implements FoodFactory{

    @Override
    public Food getFood() {
        return new F2();
    }

}
