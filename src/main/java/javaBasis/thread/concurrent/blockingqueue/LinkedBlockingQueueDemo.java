package javaBasis.thread.concurrent.blockingqueue;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/*
基于链表实现的阻塞队列，想比与不阻塞的 ConcurrentLinkedQueue
LinkedBlockingQueue中的锁是分离的，生产者的锁PutLock，消费者的锁takeLock
而ArrayBlockingQueue生产者和消费者使用的是同一把锁；
* */
public class LinkedBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>(3);
        try {
            blockingQueue.put(1);
            blockingQueue.put(2);
            blockingQueue.put(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        int i = new Random().nextInt(100);
                        Thread.sleep(2000);
                        blockingQueue.put(i);
                        System.out.println("put from producer:  "+ i );
                    } catch (InterruptedException e) {
                    }
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Integer i = blockingQueue.take();
                        System.out.println("take from consumer: " + i);
                    } catch (InterruptedException e) {
                    }
                }

            }
        });

        consumer.start();
        Thread.sleep(1000);
        producer.start();

    }
}
