package multiThread;
/*
 *学 高洪岩 《java多线程编程技术核心》
 */
public class Lesson1 extends Thread{
	private int i;
	
	@Override
	public void run()
	{
		for(;i<100;i++)
		{
			System.out.println(getName()+"   "+i);
			
		}
		
		
	}
	
	public static void main(String[] args) {
		for(int i=0;i<100;i++)
		{
			System.out.println(Thread.currentThread().getName()+"   "+i);
			if(i==20)
			{
				new Lesson1().start();
				
				new Lesson1().start();
			}
		}

	}

}
