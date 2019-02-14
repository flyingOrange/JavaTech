package designPattern.decorator;
//装饰类
public class Decorator implements Component{
    
    private Component component;
    
    //注入 具体实现类ConcretComponent,从而实现了完全相同的功能
    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void biu() {
        component.biu();
    }

}
