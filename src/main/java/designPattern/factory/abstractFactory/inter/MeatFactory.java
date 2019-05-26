package designPattern.factory.abstractFactory.inter;

public interface MeatFactory {
    //实例化Pork
    public Meat getPork();
    
    //实例化Beef
    public Meat getBeef();
    
    //public Meat getMutton();
}
