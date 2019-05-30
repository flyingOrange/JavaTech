package designPattern.mediator.good;

public class Man extends Human{

    public Man(String name, int condition,Mediator mediator) {
        super(name, condition,mediator);
        
    }

    @Override
    public void getpartner(Human human) {
        this.getMediator().setMan(this);
        this.getMediator().getpartner(human);   
    }

}
