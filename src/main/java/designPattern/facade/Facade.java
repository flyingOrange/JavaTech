package designPattern.facade;

public class Facade {
    private SystemA systemA;
    private SystemB systemB;
    private SystemC systemC;
    
    public Facade() {
        systemA = new SystemA();
        systemB = new SystemB();
        systemC = new SystemC();
    }

    public void doSomething() {
        systemA.doSomething();
        systemB.doSomething();
        systemC.doSomething();
    }
    
    public void doAB() {
        systemA.doSomething();
        systemB.doSomething();
    }

    
}
