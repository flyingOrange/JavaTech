package designPattern.factory.abstractFactory;

import designPattern.factory.abstractFactory.absclass.Pork;

public class ChinesePork extends Pork {

    @Override
    public void get() {
        System.out.println("中国猪肉");
    }
}
