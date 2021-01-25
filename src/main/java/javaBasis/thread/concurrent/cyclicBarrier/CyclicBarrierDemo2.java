package javaBasis.thread.concurrent.cyclicBarrier;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
CyclicBarrier指定了计数器为2，线程A和线程B都分别执行了2次cyclicBarrier.await();
这样导致的效果就是：每个线程分阶段完成任务，等所有线程都完成了第1步，然后才能接着执行第2步，等所有线程都完成了第2步，才能执行第3步。
* */
public class CyclicBarrierDemo2 {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 将线程A添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("线程A：" + Thread.currentThread().getName() + "执行第1步。");
                    cyclicBarrier.await();
                    System.out.println("线程A：" + Thread.currentThread().getName() + "执行第2步。");
                    cyclicBarrier.await();
                    System.out.println("线程A：" + Thread.currentThread().getName() + "执行第3步。");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 将线程B添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("线程B：" + Thread.currentThread().getName() + "执行第1步。");
                    cyclicBarrier.await();
                    System.out.println("线程B：" + Thread.currentThread().getName() + "执行第2步。");
                    cyclicBarrier.await();
                    System.out.println("线程B：" + Thread.currentThread().getName() + "执行第3步。");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 关闭线程池
        executorService.shutdown();
    }
}
