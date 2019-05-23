package designPattern.factory.factoryMethod;

//获取一个Food的实例时,实现FoodFactory接口(称为抽象工厂),则不用修改原有的工厂类

public interface FoodFactory{
    public Food getFood();
}
