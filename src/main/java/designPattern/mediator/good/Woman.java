package designPattern.mediator.good;

public class Woman extends Human{

    public Woman(String name, int condition,Mediator mediator) {
        super(name, condition,mediator);
        
    }

    @Override
    public void getpartner(Human human) {
        this.getMediator().setWoman(this);
        this.getMediator().getpartner(human);        
    }

    
}
