package javaBasis.thread.concurrent.blockingqueue;

import designPattern.builder.Main;

import java.util.concurrent.PriorityBlockingQueue;

/*
构造时可以传入一个比较器，可以看做放进去的元素会被排序，然后读取的时候按顺序消费。某些低优先级的元素可能长期无法被消费，因为不断有更高优先级的元素进来。
放，取，移除 的时候都加锁，同时只能一个线程操作。
* */
public class PriorityBlockingQueueDemo {
    public static void main(String[] args) {
        PriorityBlockingQueue<Integer> bp = new PriorityBlockingQueue<Integer>();
        bp.put(99);
        bp.put(36);
        bp.put(74);
        bp.put(40);

        try {
            //对象实现Comparable接口，优先取出
            Integer i = bp.take();
            System.out.println(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
