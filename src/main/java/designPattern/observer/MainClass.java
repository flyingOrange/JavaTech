package designPattern.observer;
/*
 * 观察者模式
 * 当对象间存在一对多关系时，则使用观察者模式（Observer Pattern）。比如，当一个对象被修改时，则会自动通知它的依赖对象。
 * 这种模式有时又称作发布-订阅模式、模型-视图模式，观察者模式属于行为型模式。
 * 观察者模式的主要角色如下。
    抽象主题（Subject）角色：也叫抽象目标类，它提供了一个用于保存观察者对象的聚集类和增加、删除观察者对象的方法，以及通知所有观察者的抽象方法。
    具体主题（Concrete Subject）角色：也叫具体目标类，它实现抽象目标中的通知方法，当具体主题的内部状态发生改变时，通知所有注册过的观察者对象。
    抽象观察者（Observer）角色：它是一个抽象类或接口，它包含了一个更新自己的抽象方法，当接到具体主题的更改通知时被调用。
    具体观察者（Concrete Observer）角色：实现抽象观察者中定义的抽象方法，以便在得到目标的更改通知时更新自身的状态。
 * */
public class MainClass {
    public static void main(String[] args) {
        /*观察者模式典型应用:
         * 1、监听事件驱动程序设计中的外部事件
         * 2、监听某个对象是否发生变化
         * 3、发布者/订阅者
        * */
        Subject subject = new ConcreteSubject();
        Observer obs1 = (Observer) new ConcreteObserver1();
        Observer obs2 = (Observer) new ConcreteObserver2();
        subject.add(obs1);
        subject.add(obs2);
        subject.notifyObserver();

    }
    

}
