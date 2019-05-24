package designPattern.factory.abstractFactory;

import designPattern.factory.abstractFactory.absclass.Pork;

public class JanpanPork extends Pork{

    @Override
    public void get() {
        System.out.println("日本猪肉");
    }

}
