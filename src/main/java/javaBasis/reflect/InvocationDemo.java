package javaBasis.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


interface People
{
	void walk();
	void say(String word);
	
}
class MyInvocationHandler implements InvocationHandler
{

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable 
	{
		System.out.println("-----正在执行的方法:"+method);
		if(args != null)
		{
			System.out.println("传入实际参:");
			for(Object val : args)
			{
				System.out.println(val);
				
			}
		}else
		{
			System.out.println("无实参");
		}
		
		return null;
	}
	

}

public class InvocationDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InvocationHandler handler = new MyInvocationHandler();
		People people = (People)Proxy.newProxyInstance(People.class.getClassLoader(), new Class[]{People.class}, handler);
	
		people.walk();
		people.say("hah");
	}

}
