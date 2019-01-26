package designPattern.InterfaceAndAbsclass.test2;

import designPattern.InterfaceAndAbsclass.InterfaceMulti;

public abstract class AbstractClass2 implements InterfaceMulti{

    @Override
    public void hello() {
        System.out.println("AbsClass2 hello");
    }

    
}
