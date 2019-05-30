package designPattern.mediator.bad;
/*
 * 中介者模式:是用来降低多个对象和类之间的通信复杂性。
 * 这种模式提供了一个中介类，该类通常处理不同类之间的通信，并支持松耦合，使代码易于维护
 * 
 * */
public class MainClass {
    public static void main(String[] args) {
        Human zhangsan = new Man("张三",5);
        Human xiaofang = new Woman("小芳",6);
        //存在类之间的交互行为,中介者模式通过mediator
        zhangsan.getpartner(xiaofang);
    }

}
