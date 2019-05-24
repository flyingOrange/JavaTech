package designPattern.factory.abstractFactory;

import designPattern.factory.abstractFactory.inter.Meat;

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
