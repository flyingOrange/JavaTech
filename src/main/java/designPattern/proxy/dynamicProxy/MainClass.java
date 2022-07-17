package designPattern.proxy.dynamicProxy;

import designPattern.proxy.RealSubject;
import designPattern.proxy.Subject;

import java.lang.reflect.Proxy;

/*动态代理有两种，jdk提供的和cglib
 * jdk动态代理不需要我们手写ProxySubject，而是jdk生成的。
 * 缺点:它始终无法摆脱仅支持interface代理的桎梏,因为它的设计注定了这个遗憾。回想一下那些动态生成的代理类的继承关系图,它们已经注定有一个共同的父类叫Proxy。
 * Java的继承机制注定了这些动态代理类们无法实现对 class 的动态代理，原因是多继承在 Java 中本质上就行不通。
 * cglib代理：对类生成代理对象。
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
