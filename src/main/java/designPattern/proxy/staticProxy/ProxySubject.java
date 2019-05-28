package designPattern.proxy.staticProxy;

import designPattern.proxy.RealSubject;
import designPattern.proxy.Subject;

public class ProxySubject implements Subject{
    private RealSubject realSubject;
    
    public void setRealSubject(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void sellBook() {
        if(realSubject == null) {
            realSubject = new RealSubject();
        }
        dazhe();
        this.realSubject.sellBook();
        give();
    }
    
    public void dazhe() {
        System.out.println("打折");
    }

    public void give() {
        System.out.println("赠送优惠券");
    }

    

}
