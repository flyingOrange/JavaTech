package javaBasis.thread.concurrent.queue;

import java.util.concurrent.LinkedTransferQueue;

/*
LinkedTransferQueue是一个由链表结构组成的无界阻塞TransferQueue队列。相对于其他阻塞队列，LinkedTransferQueue多了tryTransfer和transfer方法。
可以算是 LinkedBolckingQueue 和 SynchronousQueue 和合体。
LinkedTransferQueue是一种无界阻塞队列，底层基于单链表实现,其内部节点分为数据结点、请求结点；基于CAS无锁算法实现

* LinkedTransferQueue采用一种预占模式。意思就是消费者线程取元素时，如果队列不为空，则直接取走数据，若队列为空，
* 那就生成一个节点（节点元素为null）入队，然后消费者线程被等待在这个节点上，后面生产者线程入队时发现有一个元素为null的节点，
* 生产者线程就不入队了，直接就将元素填充到该节点，并唤醒该节点等待的线程，被唤醒的消费者线程取走元素，从调用的方法返回。我们称这种节点操作为“匹配”方式。
* */
public class LinkedTransferQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<Integer> queue = new LinkedTransferQueue<Integer>();

        Thread putThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("put thread start");
                //queue.put(1);
                System.out.println("put thread end");
            }
        });

        Thread takeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("take thread start");
                try {
                    System.out.println("take from putThread: " + queue.take());
                } catch (InterruptedException e) {
                }
                System.out.println("take thread end");
            }
        });

        takeThread.start();
        Thread.sleep(5000);
        putThread.start();

//        takeThread.start();
//        Thread.sleep(5000);
//        putThread.start();

    }


}
