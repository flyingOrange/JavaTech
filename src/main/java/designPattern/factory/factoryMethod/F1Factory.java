package designPattern.factory.factoryMethod;

public class F1Factory implements FoodFactory{

    @Override
    public Food getFood() {
        return new F1();
    }

}
