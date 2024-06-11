package javaBasis.java8newFeatures.functionalInterface;

import org.junit.Test;

public class FunctionalTest {

    /**
     * 无参数函数式接口
     */
    @Test
    public void testNoPar() {
        //无参数，只有一条语句，可省略大括号
        FunctionalInterfaceNoParam finp1 = () -> System.out.println("No param");
        finp1.single();

        FunctionalInterfaceNoParam finp2 = () -> {
            System.out.print("No param & ");
            System.out.println("one sentence");
        };
        finp2.single();
    }


    /**
     * 需要单个参数,几种不同写法
     */
    @Test
    public void testOnePar() {
        MyFunctionalInterface myF1 = (message) -> System.out.println(message);
        myF1.single("msg");

        MyFunctionalInterface myF2 =  (message) -> {
            System.out.print("two sentences ");
            System.out.println(message);
        };
        myF2.single("msg2");

        MyFunctionalInterface myF3 =  message -> {
            System.out.println(message);
        };
        myF3.single("msg3");


    }

    /*
     * 多参数，有返回值
     */
    @Test
    public void testOneParameter(){
        MyFunctionalInterface2 myFunctionalInterface2 = (msg1,msg2)->{
            System.out.println(msg1+","+msg2);
            return msg1+msg2;
        };
        myFunctionalInterface2.single2("ww", "ffff");
        
    }
}
