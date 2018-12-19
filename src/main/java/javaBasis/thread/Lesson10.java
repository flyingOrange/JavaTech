package javaBasis.thread;
/**
 * ThreadLocal类  使用方法
 * ThreadLocal用来保存每个线程的独有的数据
 * 
 */
class Tools
{
	public static ThreadLocal tl = new ThreadLocal();
}
class ThreadA11 extends Thread
{	
	@Override
	public void run()
	{
		try{
			for(int i=0;i<100;i++)
			{
				Tools.tl.set("ThreadA"+(i+1));
				System.out.println("ThreadA get value="+Tools.tl.get());
				Thread.sleep(200);
			}
			
			
		}catch(InterruptedException e)
		{
			e.printStackTrace();
			
		}
	}
}
class ThreadB11 extends Thread
{	
	@Override
	public void run()
	{
		try{
			for(int i=0;i<100;i++)
			{
				Tools.tl.set("ThreadB"+(i+1));
				System.out.println("ThreadB get value="+Tools.tl.get());
				Thread.sleep(200);
			}
			
		}catch(InterruptedException e)
		{
			e.printStackTrace();
			
		}
	}
}
public class Lesson10 {
	public static void main(String[] args) {
		ThreadA11 a = new ThreadA11();
		ThreadB11 b = new ThreadB11();
		a.start();
		b.start();
		try{
			for(int i=0;i<100;i++)
			{
				Tools.tl.set("main"+(i+1));
				System.out.println("main get value="+Tools.tl.get());
				Thread.sleep(200);
			}
			
		}catch(InterruptedException e)
		{
			e.printStackTrace();
			
		}
		
	}
}
