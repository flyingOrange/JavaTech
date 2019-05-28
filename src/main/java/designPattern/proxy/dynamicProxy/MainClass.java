package designPattern.proxy.dynamicProxy;

import java.lang.reflect.Proxy;

/*动态代理有两种，jdk提供的和cglib
 * jdk动态代理不需要我们手写ProxySubject，而是jdk生成的
 * 
 * */
public class MainClass {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        MyHandler handler = new MyHandler();
        handler.setRealSubject(realSubject);

        Subject subjectProxy = (Subject)Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                realSubject.getClass().getInterfaces(), handler);
        subjectProxy.sellBook();

    }

}
