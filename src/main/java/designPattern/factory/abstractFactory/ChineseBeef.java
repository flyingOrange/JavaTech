package designPattern.factory.abstractFactory;

import designPattern.factory.abstractFactory.absclass.Beef;

public class ChineseBeef extends Beef{

    @Override
    public void get() {
        System.out.println("中国牛肉");
    }

}
