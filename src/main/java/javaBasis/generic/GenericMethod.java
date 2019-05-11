package javaBasis.generic;

//泛型类，是在实例化类的时候指明泛型的具体类型；泛型方法，是在调用方法的时候指明泛型的具体类型 
//public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法

public class GenericMethod {

	public static <T> T getMiddle(T... list) {
		if (list.length > 0) {
			int mid = list.length / 2;
			return list[mid];
		}else
		{
			return null;
		}
	}
	

}
