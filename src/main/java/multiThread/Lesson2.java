package multiThread;
/*
 *学 高洪岩 《java多线程编程技术核心》
 *
 *两个线程访问同一对象中的同步方法    是安全的！
 *
 */
class MuitiThread
{
	private int num = 0;
	/*synchronized*/ void add(String username)
	{
		if(username.equals("a"))
		{
			num = 100;
			System.out.println("a set over");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			num = 200;
			System.out.println("b set over");
			
		}
		System.out.println(username+"  num = "+num);
			
		
	}

}
class ThreadA extends Thread
{
	private MuitiThread ref;
	public ThreadA(MuitiThread ref)
	{
		this.ref = ref;
	}
	@Override
	public void run()
	{
		ref.add("a");
	}

}
class ThreadB extends Thread
{
	private MuitiThread ref;
	public ThreadB(MuitiThread ref)
	{
		this.ref = ref;
	}
	@Override
	public void run()
	{
		ref.add("b");
	}

}
public class Lesson2 {
	public static void main(String[] args) {
		MuitiThread ref = new MuitiThread();
		
		ThreadA tA = new ThreadA(ref);
		tA.start();
		ThreadB tB = new ThreadB(ref);
		tB.start();
		
		/*
		 
		a set over
		b set over
		b  num = 200
		a  num = 200
		
		本意是输出a num = 100
		但是由于线程安全问题，数据脏了
		
		如果在add方法前修饰synchronized则不会出现脏数据
		 */
		
	}

}
