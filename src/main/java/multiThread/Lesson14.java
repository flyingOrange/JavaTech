package multiThread;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
/**
 * 使用多个 Condition实现通知部分线程
 */

class MyService2 
{
	private Lock lock = new ReentrantLock();
	private Condition conditionA = lock.newCondition();
	private Condition conditionB = lock.newCondition();
	public void awaitA()
	{
		try {
			lock.lock();
			System.out.println(" begin awaitA时间为  "+System.currentTimeMillis()+"  ThreadName="+Thread.currentThread().getName());
			conditionA.await();
			System.out.println(" end awaitA时间为  "+System.currentTimeMillis()+"  ThreadName="+Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally
		{
			lock.unlock();
		}
	}
	public void awaitB()
	{
		try {
			lock.lock();
			System.out.println(" begin awaitB时间为  "+System.currentTimeMillis()+"  ThreadName="+Thread.currentThread().getName());
			conditionB.await();
			System.out.println(" end awaitB时间为  "+System.currentTimeMillis()+"  ThreadName="+Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally
		{
			lock.unlock();
		}
	}
	public void signalAll_A()
	{
		try{
			lock.lock();
			System.out.println(" signalAll_A时间为  "+System.currentTimeMillis()+"  ThreadName="+Thread.currentThread().getName());
			conditionA.signalAll();
		}finally{
			lock.unlock();
		}
	}
	public void signalAll_B()
	{
		try{
			lock.lock();
			System.out.println(" signalAll_B时间为  "+System.currentTimeMillis()+"  ThreadName="+Thread.currentThread().getName());
			conditionB.signalAll();
		}finally{
			lock.unlock();
		}
	}
}
class Threa_A extends Thread
{
	private MyService2 service;
	public Threa_A(MyService2 service)
	{
		super();
		this.service = service;
	}
	@Override
	public void run()
	{
		service.awaitA();
	}
}
class Threa_B extends Thread
{
	private MyService2 service;
	public Threa_B(MyService2 service)
	{
		super();
		this.service = service;
	}
	@Override
	public void run()
	{
		service.awaitB();
	}
}
public class Lesson14 {
	public static void main(String[] args) throws InterruptedException {
		MyService2 service = new MyService2();
		Threa_A a = new Threa_A(service);
		a.setName("A");
		a.start();
		Threa_B b = new Threa_B(service);
		b.setName("B");
		b.start();
		
		Thread.sleep(3000);
		service.signalAll_A();
		
	}

}
