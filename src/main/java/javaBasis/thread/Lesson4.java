package javaBasis.thread;
/**
 * 多线程的死锁
 */
class DeadLock implements Runnable
{
	public String username;
	public Object lock1 = new Object();
	public Object lock2 = new Object();
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void run() {
		if(username.equals("a"))
		{
			synchronized(lock1)
			{
				try{
					System.out.println("username = "+username);
					Thread.sleep(3000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				synchronized(lock2)
				{
					System.out.println("按lock1--->lock2顺序执行");
				}	
			}
		}
		if(username.equals("b"))
		{
			synchronized(lock2)
			{
				try{
					System.out.println("username = "+username);
					Thread.sleep(3000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				synchronized(lock1)
				{
					System.out.println("按lock2--->lock1顺序执行");
				}	
			}
		}	
	}
	

}

public class Lesson4 {

	
	public static void main(String[] args) {
		DeadLock t1 = new DeadLock();
		t1.setUsername("a");
		Thread thread1 = new Thread(t1);
		thread1.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t1.setUsername("b");
		Thread thread2 = new Thread(t1);
		thread2.start();
		
		
	}

}

