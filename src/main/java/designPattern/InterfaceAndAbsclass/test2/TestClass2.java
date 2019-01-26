package designPattern.InterfaceAndAbsclass.test2;

import designPattern.InterfaceAndAbsclass.InterfaceMulti;

public class TestClass2 extends AbstractClass2 implements InterfaceMulti {


    @Override
    public void hi() {
        System.out.println("TestClass2 hi");
    }

    @Override
    public void howAreU() {
        System.out.println("TestClass2 howAreU");
    }

    public static void main(String[] args) {
        // AbsClass2已经实现了InterfaceMulti接口,AbsClass2同时继承AbsClass2并实现InterfaceMulti接口
        TestClass2 test = new TestClass2();
        test.hello();//调用AbsClass2 hello()方法
        test.hi();//调用TestClass2重写的hi()方法
        test.howAreU();//调用TestClass2重写的howAreU()方法
    }

}
