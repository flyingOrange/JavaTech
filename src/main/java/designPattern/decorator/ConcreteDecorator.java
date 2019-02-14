package designPattern.decorator;
//具体装饰类
public class ConcreteDecorator extends Decorator{

    public ConcreteDecorator(Component component) {
        super(component);
    }
    
    //重写biu()方法,实现了对其增强的效果
    @Override
    public void biu() {
        System.out.println("start");
        this.biu();
        System.out.println("end");
    }

}
