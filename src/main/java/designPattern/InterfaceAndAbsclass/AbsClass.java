package designPattern.InterfaceAndAbsclass;

public abstract class AbsClass implements InterfaceSon{
    
    @Override
    public String say() {
        System.out.println("abs say");
        return null;
    }

    @Override
    public String eat() {
        System.out.println("abs eat");
        return null;
    }
    
    abstract void drink();
    
}
