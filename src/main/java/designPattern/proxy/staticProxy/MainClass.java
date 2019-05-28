package designPattern.proxy.staticProxy;

import designPattern.proxy.RealSubject;

/*静态代理:可以做到在不修改目标对象的功能前提下,对目标功能扩展
缺点:代理对象需要与目标对象实现一样的接口,所以会有很多代理类,类太多.同时,一旦接口增加方法,目标对象与代理对象都要维护

Subject--真实主题与代理主题的共同接口
RealSubject--目标对象
Proxy--保存RealSubject的引用
*/
public class MainClass {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        realSubject.sellBook();
        
        ProxySubject proxySubject = new ProxySubject();
        proxySubject.sellBook();
        
    }

}
