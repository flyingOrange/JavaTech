package designPattern.observer;
/*
 * 观察者模式
 * 当对象间存在一对多关系时，则使用观察者模式（Observer Pattern）。比如，当一个对象被修改时，则会自动通知它的依赖对象。观察者模式属于行为型模式
 * Subject-被观察者    ConcretSubject-被观察者具体实现
 * Observer-观察者(接口或者抽象类)    ConcretObserver-观察者具体实现
 * */
public class MainClass {
    public static void main(String[] args) {
        /*观察者模式典型应用:
         * 1、监听事件驱动程序设计中的外部事件
         * 2、监听某个对象是否发生变化
         * 3、发布者/订阅者
         * 
         * */
        Person person = new Person();
        //注册观察者
        person.addObserver(new MyObserver());
        //删除所有观察者
        //person.deleteObservers();
        //观察者数量
        //int count = person.countObservers();
        
        person.setName("orange");
    }
    

}
