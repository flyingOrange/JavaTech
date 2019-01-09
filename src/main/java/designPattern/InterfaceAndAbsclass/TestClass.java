package designPattern.InterfaceAndAbsclass;

public class TestClass extends AbsClass{

    @Override
    void drink() {
        System.out.println("TestClass drink");
        
    }
    
    @Override
    public String say() {
        System.out.println("TestClass say");
        return null;
    }
    
    public static void main(String[] args) {
        TestClass test = new TestClass();
        test.drink();
        test.eat();
        test.say();
        
    }



}
