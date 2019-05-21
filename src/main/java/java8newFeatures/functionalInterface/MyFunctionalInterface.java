package java8newFeatures.functionalInterface;
//@FunctionalInterface的作用就是标识一个接口为函数式接口,lambda表达式用于替换函数式接口
//接口中有且只有一个抽象方法，则可以被注解为函数式接口
@FunctionalInterface
public interface MyFunctionalInterface  {
	public void single(String msg);

}


