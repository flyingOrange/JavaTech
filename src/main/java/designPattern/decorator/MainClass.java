package designPattern.decorator;

/*
 * 装饰器模式
 * */
public class MainClass {
    public static void main(String[] args) {
        //原有biu方法
        ConcretComponent concretComponent = new ConcretComponent();
        concretComponent.biu();
        //装饰后的biu方法，不修改原有实现的情况下增加了装饰性操作
        ConcreteDecorator concreteDecorator = new ConcreteDecorator(concretComponent);
        concreteDecorator.biu();
    }
}
