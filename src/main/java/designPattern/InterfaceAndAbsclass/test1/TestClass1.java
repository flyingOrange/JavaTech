package designPattern.InterfaceAndAbsclass.test1;

public class TestClass1 extends AbstractClass1{

    //实现AbsClass1的抽象方法drink()
    @Override
    void drink() {
        System.out.println("TestClass1 drink");
    }
    
    //覆盖AbsClass1已经实现的接口InterfaceFather的say()方法
    @Override
    public String say() {
        System.out.println("TestClass1 say");
        return null;
    }
    
    public static void main(String[] args) {
        TestClass1 test = new TestClass1();
        test.drink();//调用TestClass1中实现的drink()方法
        test.eat();//调用AbsClass1中实现的eat()方法
        test.say();//调用TestClass1中重写的say()方法
    }



}
