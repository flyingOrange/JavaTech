package javaBasis.thread;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * ReentrantLock重入锁
 * 
 */
class MyService
{
	private Lock lock = new ReentrantLock();
	public void methodA()
	{
		try{
			lock.lock();
			System.out.println("methodA begin ThreadName="+Thread.currentThread().getName()+"   time="+System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("methodA end ThreadName="+Thread.currentThread().getName()+"   time="+System.currentTimeMillis());
						
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	public void methodB()
	{
		try{
			lock.lock();
			System.out.println("methodB begin ThreadName="+Thread.currentThread().getName()+"   time="+System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("methodB end ThreadName="+Thread.currentThread().getName()+"   time="+System.currentTimeMillis());
						
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}
class ThreadAA extends Thread
{
	private MyService service;
	public ThreadAA(MyService service)
	{
		super();
		this.service = service;
	}
	@Override
	public void run()
	{
		service.methodA();
	}
}
class ThreadAA1 extends Thread
{
	private MyService service;
	public ThreadAA1(MyService service)
	{
		super();
		this.service = service;
	}
	@Override
	public void run()
	{
		service.methodA();
	}
}
class ThreadBB extends Thread
{
	private MyService service;
	public ThreadBB(MyService service)
	{
		super();
		this.service = service;
	}
	@Override
	public void run()
	{
		service.methodB();
	}
}
class ThreadBB2 extends Thread
{
	private MyService service;
	public ThreadBB2(MyService service)
	{
		super();
		this.service = service;
	}
	@Override
	public void run()
	{
		service.methodB();
	}
}

public class Lesson12 {
	public static void main(String[] args) {
		MyService service = new MyService();
		ThreadAA a = new ThreadAA(service);
		a.setName("A");
		a.start();
		
		ThreadAA1 aa = new ThreadAA1(service);
		aa.setName("AA");
		aa.start();
		
		ThreadBB b = new ThreadBB(service);
		b.setName("B");
		b.start();
		
		ThreadBB2 bb = new ThreadBB2(service);
		bb.setName("BB");
		bb.start();
	}
}
