package javaBasis.thread.concurrent.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {

	public static void main(String[] args) {
		
		RunnableService runnableService = new RunnableService("张三", 5000);
		ThreadService threadService = new ThreadService("lisi", 2000);
		
		//创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
		ExecutorService singlePool = Executors.newSingleThreadExecutor();
		singlePool.execute(runnableService);
		
		//创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。因为线程池大小为2，每个任务输出index后sleep 2秒，所以每两秒打印。
		//定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()
		ExecutorService fixedPool = Executors.newFixedThreadPool(2);
		fixedPool.execute(threadService);
		fixedPool.execute(runnableService);

		//创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程，线程池为无限大，
		//当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
		ExecutorService cachePool = Executors.newCachedThreadPool();
		cachePool.execute(threadService);
		cachePool.execute(runnableService);

		//创建一个定长线程池，支持定时及周期性任务执行。延迟执行
		ExecutorService schedualPool = Executors.newScheduledThreadPool(5);
		schedualPool.execute(threadService);
		schedualPool.execute(runnableService);
	}

}
