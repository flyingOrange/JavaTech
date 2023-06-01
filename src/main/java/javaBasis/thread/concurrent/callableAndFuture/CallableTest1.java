package javaBasis.thread.concurrent.callableAndFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * 假如有这样的场景，我们现在需要计算一个数据，而这个数据的计算比较耗时，而我们后面的程序也要用到这个数据结果，那么这个时Callable岂不是最好的选择？
 * 我们可以开设一个线程去执行计算，而主线程继续做其他事，而后面需要使用到这个数据时，我们再使用Future获取不就可以了吗？下面我们就来编写一个这样的实例。
 *
 *    
 *    使用Callable+Future获取执行结果
 *
 * */
public class CallableTest1 {

	public static void main(String[] args) {
		//创建线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        //创建Callable对象任务  
        Task task = new Task();
        //提交任务并获取执行结果  
        Future<Integer> result = executor.submit(task);
        //关闭线程池  
        executor.shutdown();
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
         
        System.out.println("主线程在执行任务");
         
        try {
            if(result.get()!=null){  
                System.out.println("task运行结果"+result.get());
            }else{
                System.out.println("未获取到结果"); 
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
         
        System.out.println("所有任务执行完毕");

	}

}
