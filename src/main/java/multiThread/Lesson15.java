package multiThread;
/*
 JDK自带线程池用法
 
 */
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Lesson15 {

	
	public static void main(String[] args) throws InterruptedException {
		/////////////////////////newFixedThreadPool：创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。/////////////////////////////
		/*ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 100; i++) 
        {

            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() 
                {
                    System.out.println(Thread.currentThread());
                }
            });
        }
        //////////////////////newScheduledThreadPool：支持定时和周期性执行的线程池。/////////////////////////////
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        //延迟3秒执行
        scheduledThreadPool.schedule(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread());
            }
        }, 3000, TimeUnit.MILLISECONDS);

        //延迟1秒后每3秒执行一次
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread());
            }
        }, 1, 3000, TimeUnit.MILLISECONDS);*/
		
		//newSingleThreadExecutor：此线程池是一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序。
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            singleThreadExecutor.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println(Thread.currentThread());
                }
            });
        }
      
        
	}
}






