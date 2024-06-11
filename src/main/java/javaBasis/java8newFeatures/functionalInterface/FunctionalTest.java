package javaBasis.java8newFeatures.functionalInterface;

import org.junit.Test;

public class FunctionalTest {
    /**
     * 需要单个参数
     */
    @Test
    public void testOnePar() {
        MyFunctionalInterface myFunctionalInterface = (String elem) -> {
            System.out.println(elem);
        };
        myFunctionalInterface.single("msg");
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
