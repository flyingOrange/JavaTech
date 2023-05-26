package javaBasis.thread;
/*
 volatile的用法:使变量在多个线程间可见，但不支持原子性 
 
 volatile的作用是强制从公共堆栈中取得变量的值，而不是从线程私有数据栈中取得变量的值。
 volatile能保证可见性，但不能保证原子性。synchronized可以保证原子性，也间接保证了可见性
 */
class RunThread extends Thread {
	volatile private boolean isRunning = true;
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	public boolean isRunning() {
		return isRunning;
	}
	public void run() {
		System.out.println("进入run");
		while(isRunning() == true) {
		}
		System.out.println("线程被停止了");
	}
	
}

public class Lesson5 {

	public static void main(String[] args) throws InterruptedException {
		RunThread t = new RunThread();
		t.start();
		Thread.sleep(5*1000);
		t.setRunning(false);
		System.out.println("已赋值为false");

	}

}






