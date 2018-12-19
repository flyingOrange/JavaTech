package javaBasis.thread;
/**
 * join()方法的使用
 * 
 * 
 */

class MyThread extends Thread
{
	
	@Override
	public void run()
	{
		try{
			int secondValue = (int) (Math.random()*1000+3000);
			System.out.println(secondValue);
			Thread.sleep(secondValue);
			
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
public class Lesson8 {

	
	public static void main(String[] args) {
		try {
			MyThread t = new MyThread();
			t.start();
			t.join();
			//Thread.sleep(800);
			
			System.out.println("MyThread t 执行完毕后，我再执行啊！");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
