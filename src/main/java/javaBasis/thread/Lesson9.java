package javaBasis.thread;
/**
 * join(int long)和sleep(int long)的区别
 * 
 */

class ThreadA1 extends Thread
{
	private ThreadB1 b;
	public ThreadA1(ThreadB1 b)
	{
		super();
		this.b = b;
	}
	
	@Override
	public void run()
	{
		try{
			
			synchronized(b)
			{
				b.start();
				//b.join();
				Thread.sleep(6000);
				//Thread.sleep();不释放锁,join()释放
			}
			
		}catch(InterruptedException e)
		{
			e.printStackTrace();
			
		}
	}
}

class ThreadB1 extends Thread
{
	@Override
	public void run()
	{
		try{
			System.out.println("b run begin timer="+System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("b run end timer="+System.currentTimeMillis());
			
		}catch(InterruptedException e)
		{
			e.printStackTrace();
			
		}
	}
	
	synchronized public void bService()
	{
		System.out.println("bService timer="+System.currentTimeMillis());
		
	}
	
}

class ThreadC1 extends Thread
{
	private ThreadB1 threadB1;
	public ThreadC1(ThreadB1 threadB1)
	{
		super();
		this.threadB1 = threadB1;
	}
	@Override
	public void run()
	{
		threadB1.bService();
	}
}
public class Lesson9 {


	public static void main(String[] args) {
		try{
			ThreadB1 b = new ThreadB1();
			ThreadA1 a = new ThreadA1(b);
			a.start();
			Thread.sleep(1000);
			ThreadC1 c = new ThreadC1(b);
			c.start();
			/*
			 * ThreadA1 使用sleep(6000)，所以一直持有ThreadB1的对象锁，
			 * 时间到达6秒后才释放ThreadB1的对象锁，因此6秒后才能执行bService()方法
			 * */
			
		}catch(InterruptedException e)
		{
			e.printStackTrace();
			
		}
	}

}
