package javaBasis.thread.concurrent.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;

/*
基于数组实现的可阻塞队列，构造时必须制定数组大小，往里面放东西时如果数组满了便会阻塞直到有位置（也支持直接返回和超时等待），
按先进先出的原则对元素进行排序。
通过一个锁 ReentrantLock 保证线程安全。
* */
public class ArrayBlockingQueueDemo {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> bq= new ArrayBlockingQueue<Integer>(3);
        //add(E e)     如果 BlockingQueue 可以容纳，则返回 true，否则报异常
        bq.add(23);
        bq.add(76);
        //bq.add(43);
        //bq.add(20);

        //put(E e)  如果 BlockQueue 没有空间，则调用此方法的线程被阻断直到 BlockingQueue 里面有空间再继续
        try {
            bq.put(69);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /******     offer/poll是一对互斥的操作，offer向队列种放入元素，poll取出元素       *****/
        //offer(E e)   如果 BlockingQueue 可以容纳，则返回 true，否则返回 false
        //bq.offer(20);
        //poll(time)：取走 BlockingQueue 里排在首位的对象，若不能立即取出，则可以等 time 参数规定的时间,取不到时返回 null
        bq.poll();

        /******     put/take是一对互斥的操作，put向队列种放入元素  ********/
        //take()：取走 BlockingQueue 里排在首位的对象，若 BlockingQueue 为空，阻断进入等待状态直到 Blocking 有新的对象被加入为止
        try {
            bq.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //bq.remainingCapacity(); 剩余可用的大小。等于初始容量减去当前的 size
        System.out.println(bq.remainingCapacity());

        System.out.println(bq);



    }

}
