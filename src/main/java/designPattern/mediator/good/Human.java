package designPattern.mediator.good;

public abstract class Human {
    
    private String name;
    private int condition;
    private Mediator mediator;
    
    public Human(String name, int condition, Mediator mediator) {
        super();
        this.name = name;
        this.condition = condition;
        this.mediator = mediator;
    }

    public abstract void getpartner(Human human);
    
    public Mediator getMediator() {
        return mediator;
    }
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCondition() {
        return condition;
    }
    public void setCondition(int condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Human [name=" + name + ", condition=" + condition + "]";
    }

}
