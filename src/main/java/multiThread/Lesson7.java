package multiThread;
/**
 * 生产者\消费者模式 的实现
 * 一生产者一消费者
 * 
 */
class ValueObject
{
	public static String value="";
}
//生产者
class Producer
{
	private String lock;
	
	public Producer(String lock)
	{
		super();
		this.lock = lock;
	}
	
	public void setValue()
	{
		try{
			synchronized(lock)
			{
				if(!ValueObject.value.equals(""))
				{
					System.out.println("setValue   wait");
					lock.wait();
				}
				String value = System.currentTimeMillis()+"_"+System.nanoTime();
				System.out.println("set的值是 "+value);
				ValueObject.value = value;
				lock.notify();
				
			}
			
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
//消费者
class Consumer
{
	private String lock;
	
	public Consumer(String lock)
	{
		super();
		this.lock = lock;
	}
	public void getValue()
	{
		try{
			synchronized(lock)
			{
				if(ValueObject.value.equals(""))
				{
					System.out.println("getValue   wait");
					lock.wait();
				}
				System.out.println("get的值是 "+ValueObject.value);
				ValueObject.value = "";
				lock.notify();
			}
			
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
		
}

class ThreadP extends Thread
{
	private Producer p;
	public ThreadP(Producer p)
	{
		super();
		this.p = p;
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			p.setValue();
		}
	}
	
}

class ThreadC extends Thread
{
	private Consumer c;
	public ThreadC(Consumer c)
	{
		super();
		this.c = c;
	}
	
	@Override 
	public void run()
	{
		while(true)
		{
			c.getValue();
		}
	}
}

public class Lesson7 {

	
	public static void main(String[] args) {
		String lock = new String("");
		Producer p = new Producer(lock);
		Consumer c = new Consumer(lock);
		ThreadP threadP = new ThreadP(p);
		ThreadC threadC = new ThreadC(c);
		threadP.start();
		threadC.start();
	}

}
