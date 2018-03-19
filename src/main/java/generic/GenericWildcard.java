package generic;

//系统通配符
//Integer是Number的子类

public class GenericWildcard {
	
	public static void showKeyValue1(GenericClass<Number> obj){
		System.out.println("泛型测试  key value is " + obj.getKey());
	}
	
	public static void showKeyValue2(GenericClass<?> obj){
		System.out.println("通配符测试  key value is " + obj.getKey());
	}
	
}
