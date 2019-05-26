package designPattern.factory.abstractFactory;

import designPattern.factory.abstractFactory.inter.Meat;
import designPattern.factory.abstractFactory.inter.MeatFactory;

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
