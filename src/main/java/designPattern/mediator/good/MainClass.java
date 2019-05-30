package designPattern.mediator.good;
/*
 * 中介者模式:是用来降低多个对象和类之间的通信复杂性。
 * 这种模式提供了一个中介类，该类通常处理不同类之间的通信，并支持松耦合，使代码易于维护
 * 
 * */
public class MainClass {
    public static void main(String[] args) {
        //Human持有Mediator的引用
        Mediator mediator = new Mediator();
        Human zhangsan = new Man("zhangsan",2,mediator);
        Human xiaofang = new Woman("xiaofang",2,mediator);
        
        zhangsan.getpartner(xiaofang);
        
    }

}
