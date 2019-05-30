package designPattern.mediator.bad;

public abstract class Human {
    
    private String name;
    private int condition;
    
    public Human(String name, int condition) {
        this.name = name;
        this.condition = condition;
    } 
    
    public abstract void getpartner(Human human);
    
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
