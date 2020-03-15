package javaBasis.thread.threadPool;

public class ThreadService extends Thread {
	private String name;
	private int sleep_second;

	public ThreadService(String name, int sleep_second) {
		super();
		this.name = name;
		this.sleep_second = sleep_second;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(sleep_second);
			System.out.println("ThreadService = " + name + "线程sleep了" + sleep_second / 1000 + "s");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
