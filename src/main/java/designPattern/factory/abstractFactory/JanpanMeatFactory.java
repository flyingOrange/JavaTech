package designPattern.factory.abstractFactory;

import designPattern.factory.abstractFactory.inter.Meat;

public class JanpanMeatFactory implements MeatFactory{

    @Override
    public Meat getPork() {
        return new JanpanPork();
    }

    @Override
    public Meat getBeef() {
        return new JanpanBeef();
    }
}
