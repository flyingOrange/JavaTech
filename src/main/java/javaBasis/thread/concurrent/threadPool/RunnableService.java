package javaBasis.thread.concurrent.threadPool;

public class RunnableService implements Runnable{
	
	private String name;
	private int sleep_second;
	
	public RunnableService(String name , int sleep_second) {
		super();
		this.name = name;
		this.sleep_second = sleep_second;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(2000);
			System.out.println("RunnableService = "+name+"线程sleep了" + sleep_second/1000 + "s");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
