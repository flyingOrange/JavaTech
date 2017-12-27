package multiThread;
import java.util.*;
/**
 * InheritableThreadLocal 使用方法
 * InheritableThreadLocal 可以在子线程中取得父线程继承下来的值
 */

class InheritableThreadLocalExt extends InheritableThreadLocal
{
	@Override
	protected Object initialValue()
	{
		return new Date().getTime();
	}
	@Override
	protected Object childValue(Object parentValue)
	{
		return parentValue+"  在子进程中加的";
	}
}
class Tool
{
	public static InheritableThreadLocalExt tl = new InheritableThreadLocalExt();
}

class ThreadA111 extends Thread
{	
	@Override
	public void run()
	{
		try{
			for(int i=0;i<10;i++)
			{
				System.out.println("ThreadA get value="+Tool.tl.get());
				Thread.sleep(100);
			}
		}catch(InterruptedException e)
		{
			e.printStackTrace();
			
		}
	}
}

public class Lesson11 {
	public static void main(String[] args) {
		try{
			for(int i=0;i<10;i++)
			{
				System.out.println("main get value="+Tool.tl.get());
				Thread.sleep(100);
			}
			Thread.sleep(5000);
			ThreadA111 a = new ThreadA111();
			a.start();
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
