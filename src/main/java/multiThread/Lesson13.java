package multiThread;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

/**
 * 使用Condition实现等待/通知
 * 
 */
class Myservice1
{
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	public void await()
	{
		try {
			lock.lock();
			System.out.println("await时间为 "+System.currentTimeMillis());
			condition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally
		{
			lock.unlock();
			System.out.println("锁释放了");
		}
	}
	
	public void signal()
	{
		try{
			lock.lock();
			System.out.println("signal时间为 "+System.currentTimeMillis());
			condition.signal();
		}finally{
			lock.unlock();
		}
	}
}
class ThreaM extends Thread
{
	private Myservice1 service;
	public ThreaM(Myservice1 service)
	{
		super();
		this.service = service;
	}
	@Override
	public void run()
	{
		service.await();
	}
}
public class Lesson13 {
	public static void main(String[] args) throws InterruptedException {
		Myservice1 service = new Myservice1();
		ThreaM m = new ThreaM(service);
		m.start();
		Thread.sleep(3000);
		service.signal();
	}

}
