package javaBasis.thread.concurrent.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
CyclicBarrier在用法上其实跟CountDownLatch十分相似，但是前者功能更加强大。

当程序多次执行countDownLatch.countDown();导致计数器n=0时，阻塞的线程都将同时被唤醒。但是此时的n已经是等于0了，也就是说这个计数器就是一次性的。
CyclicBarrier也能实现countDownLatch的功能，并且它的计数器n是可以被重置的，也就是说n=0线程被唤醒后，n又能重新回到原有值。
* */
public class CyclicBarrierDemo1 {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
        // 当计数器为0时，立即执行
        @Override
        public void run() {
            System.out.println("汇总线程：" + Thread.currentThread().getName() + " 任务合并。");
        }
    });

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 将线程A添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("线程A：" + Thread.currentThread().getName() + "执行任务。");
                    System.out.println("线程A：到达屏障点");
                    cyclicBarrier.await();
                    System.out.println("线程A：退出屏障点");
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
                    System.out.println("线程B：" + Thread.currentThread().getName() + "执行任务。");
                    System.out.println("线程B：到达屏障点");
                    cyclicBarrier.await();
                    System.out.println("线程B：退出屏障点");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 关闭线程池
        executorService.shutdown();
    }
}
