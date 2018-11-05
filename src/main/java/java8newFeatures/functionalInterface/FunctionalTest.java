package java8newFeatures.functionalInterface;

import org.junit.Test;

public class FunctionalTest {
	/**
	 * 需要单个参数
	 */
	public static void testOnePar(MyFunctionalInterface myFunctionalInterface) {
		myFunctionalInterface.single("msg");
	}
	
	/*
	 * 一个参数，可以省略参数的括号
     */
    @Test
    public void testOneParameter(){
        testOnePar(x-> System.out.println(x));
    }
}
