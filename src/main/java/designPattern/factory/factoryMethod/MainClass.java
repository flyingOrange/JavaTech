package designPattern.factory.factoryMethod;

public class MainClass {

    public static void main(String[] args) {
        /*
         * 定义FoodFactory接口(抽象工厂),以后每当需要添加新的Food不需要修改原有代码,
         * 只需要新增一个xxxFactory(具体工厂类)实现FoodFactory即可,符合开放-封闭原则。
         * 
         * 开放-封闭原则:
         * 开放,代码扩展容易。封闭,原有代码不用修改
         */
        FoodFactory foodFactory = new F1Factory();
        System.out.println(foodFactory.getFood().foodName());

        FoodFactory foodFactory2 = new F2Factory();
        System.out.println(foodFactory2.getFood().foodName());

    }

}
