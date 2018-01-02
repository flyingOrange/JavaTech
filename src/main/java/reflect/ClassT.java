package reflect;

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
	public static void main(String[] args) {
		//不使用泛型则必须强制类型转换
		Date d = (Date)Test1.getInstance("java.util.Date");
		//使用泛型则不必转换
		Date d2 = Test2.getInstance(Date.class);


	}

}
