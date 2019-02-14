package designPattern.decorator;

import org.junit.Test;

public class TestCase {

    @Test
    public void test() {
        //原有biu方法
        ConcretComponent concretComponent = new ConcretComponent();
        concretComponent.biu();
        //装饰后的biu方法
        ConcreteDecorator concreteDecorator = new ConcreteDecorator(concretComponent);
        concreteDecorator.biu();
    }
}
