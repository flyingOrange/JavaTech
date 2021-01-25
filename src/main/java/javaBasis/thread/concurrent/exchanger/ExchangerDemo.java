package javaBasis.thread.concurrent.exchanger;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
*
* */
public class ExchangerDemo {
    //先new出来Exchanger对象
    private static Exchanger<String> exchanger = new Exchanger<>();
    //用线程池创建两个线程
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);
    public static void main(String[] args) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String A = "线程A的数据";
                try {
                    String B = exchanger.exchange(A);
                    System.err.println("线程A--->线程A交换到的线程B的数据：" + B);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String B = "线程B的数据";
                try {
                    String A = exchanger.exchange(B);
                    System.err.println("线程B--->线程B交换到的线程B的数据：" + A);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.shutdown();
    }
}
