package javaBasis.reflect;

import java.util.Date;


//泛型和class类

class Test1
{
	public static Object getInstance(String className)
	{
		try{
			Class cl = Class.forName(className);
			return cl.newInstance();
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
}
class Test2
{
	public static <T> T getInstance(Class<T> cls)
	{
		try{
			return cls.newInstance();
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
}
public class ClassT {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		//不使用泛型则必须强制类型转换
		Date d = (Date)Test1.getInstance("java.util.Date");
		//使用泛型则不必转换
		Date d2 = Test2.getInstance(Date.class);

		//获取Class实例的三种方
		//1
		Class cls1 = String.class;
		//2
		String s = "Hello";
		Class cls2 = s.getClass();
		//3
		Class cls3 = Class.forName("java.lang.String");
		System.out.println((cls1 == cls2)+","+(cls1 == cls3));

		/**Class实例比较和instanceof的差别：
		 * 用instanceof不但匹配指定类型，还匹配指定类型的子类
		 * 而用==判断class实例可以精确地判断数据类型，但不能作子类型比较
		 * **/
		Integer n = new Integer(123);
		boolean b1 = n instanceof Integer; // true，因为n是Integer类型
		boolean b2 = n instanceof Number; // true，因为n是Number类型的子类

		boolean b3 = n.getClass() == Integer.class; // true，因为n.getClass()返回Integer.class
		//boolean b4 = n.getClass() == Number.class; // false，因为Integer.class!=Number.class



	}

}
