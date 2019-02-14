package designPattern.decorator;
//装饰类
public class Decorator implements Component{
    
    //此处必须是public,因为子类中要调用
    public Component component;
    
    //注入 具体实现类ConcretComponent,从而实现了完全相同的功能
    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void biu() {
        component.biu();
    }

}
