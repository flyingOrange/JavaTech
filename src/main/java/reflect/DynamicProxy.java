package reflect;

//动态代理和AOP

interface Dog
{
	void info();
	void run();
}
class DogUtil
{
	public void method1()
	{
		System.out.println("通用方法一");
	}
	public void method2()
	{
		System.out.println("通用方法二");
	}
}
class GunDog implements Dog
{
	@Override
	public void info() {
		System.out.println("猎狗");
		
	}

	@Override
	public void run() {
		System.out.println("奔跑");
		
	}
	
}
public class DynamicProxy{


	public static void main(String[] args) {
		
		
		
	}

	

}
