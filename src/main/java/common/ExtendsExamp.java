package common;

public class ExtendsExamp {

	
	public static void main(String[] args) {
		//Ostrich os = new Ostrich();
		//os.fly();
		
		//多态
		Bird bd = new Ostrich();
		bd.fly();

	}

}
class Bird
{
	public int a = 5;
	String name;
	public Bird()
	{
		
	}
	public Bird(String name)
	{
		this.name = name;
	}
	public void fly()
	{
		System.out.println("我是鸟，飞翔");
		//System.out.println(a);
	}
}
class Ostrich extends Bird
{
	public int a =10;
	public int age;
	public Ostrich(int age)
	{
		super("haha");
		this.age = age;
		
	}
	public Ostrich()
	{
		
	}
	public void fly()
	{
		//super.fly();
		System.out.println("我是鸟，只能跑");
		//System.out.println(super.a);
	}
	public void sub()
	{
		System.out.println("sub Method");
		
	}
}