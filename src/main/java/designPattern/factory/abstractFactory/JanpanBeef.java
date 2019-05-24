package designPattern.factory.abstractFactory;

import designPattern.factory.abstractFactory.absclass.Beef;

public class JanpanBeef extends Beef{

    @Override
    public void get() {
        System.out.println("日本牛肉");        
    }

}
