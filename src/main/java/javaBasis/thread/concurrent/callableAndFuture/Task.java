package javaBasis.thread.concurrent.callableAndFuture;

import java.util.Random;
import java.util.concurrent.Callable;

public class Task implements Callable<Integer>{

	@Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算 耗时5秒 ");
        Thread.sleep(5000);
        Integer result = new Random().nextInt(100);
        
        return result;
    }
	
}
