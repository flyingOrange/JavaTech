package designPattern.factory.abstractFactory;

import designPattern.factory.abstractFactory.inter.Meat;

public interface MeatFactory {
    //实例化Pork
    public Meat getPork();
    
    //实例化Beef
    public Meat getBeef();
    
    //public Meat getMutton();
}
