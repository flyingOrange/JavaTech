package java8newFeatures.functionalInterface;
//@FunctionalInterface的作用就是标识一个接口为函数式接口,lambda表达式用于替换函数式接口

//接口中   有且只有  一个抽象方法，是函数式接口
@FunctionalInterface
public interface MyFunctionalInterface  {
    //接口中   有且只有  一个抽象方法，是函数式接口
	public void single(String msg);
	
	default void hello() {
	    System.out.println("hi");
    }

}


