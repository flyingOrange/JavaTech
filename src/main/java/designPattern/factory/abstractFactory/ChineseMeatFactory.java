package designPattern.factory.abstractFactory;

import designPattern.factory.abstractFactory.inter.Meat;
import designPattern.factory.abstractFactory.inter.MeatFactory;

public class ChineseMeatFactory implements MeatFactory{

    @Override
    public Meat getPork() {
        return new ChinesePork();
    }

    @Override
    public Meat getBeef() {
        return new ChineseBeef();
    }

}
