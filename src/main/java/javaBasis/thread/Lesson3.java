package javaBasis.thread;
/*
 *学 高洪岩 《java多线程编程技术核心》
 *
 *静态同步synchronized方法和synchronized(class)代码块
 *
 */

class Service
{
	synchronized public static void printA()
	{
		System.out.println("线程名:"+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"进入printA");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("线程名:"+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"离开printA");	
	}
	
	synchronized public static void printB()
	{
		System.out.println("线程名:"+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"进入printB");
		System.out.println("线程名:"+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"离开printB");
		
	}
	synchronized public void printC()
	{
		System.out.println("线程名:"+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"进入printC");
		System.out.println("线程名:"+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"离开printC");	
	}
}
class Thread1 extends Thread
{
	private Service service;
	public Thread1(Service service)
	{
		this.service = service;
	}
	@Override
	public void run()
	{
		Service.printA();
	}
}
class Thread2 extends Thread
{
	private Service service;
	public Thread2(Service service)
	{
		this.service = service;
	}
	@Override
	public void run()
	{
		Service.printB();
	}
}
class Thread3 extends Thread
{
	private Service service;
	public Thread3(Service service)
	{
		this.service = service;
	}
	@Override
	public void run()
	{
		service.printC();
	}
}
public class Lesson3 {

	
	public static void main(String[] args) {
		Service service = new Service();
		
		Thread1 a = new Thread1(service);
		a.setName("A");
		a.start();
		
		Thread2 b = new Thread2(service);
		b.setName("B");
		b.start();
		
		Thread3 c = new Thread3(service);
		c.setName("C");
		c.start();
		
		
		/*
		线程A、B同步，因为都是对象锁
		 线程A、C不同步，因为一个是对象锁，一个是Class锁
		 
		*/
		
	}

}
