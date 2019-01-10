package designPattern.InterfaceAndAbsclass.test1;

import designPattern.InterfaceAndAbsclass.InterfaceSon;

public abstract class AbsClass1 implements InterfaceSon{
    
    @Override
    public String say() {
        System.out.println("abs1 say");
        return null;
    }

    @Override
    public String eat() {
        System.out.println("abs1 eat");
        return null;
    }
    
    abstract void drink();
    
}
