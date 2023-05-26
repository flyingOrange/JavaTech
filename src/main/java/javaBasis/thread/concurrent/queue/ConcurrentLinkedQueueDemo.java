package javaBasis.thread.concurrent.queue;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
* 基于链表实现的并发队列，使用乐观锁 (CAS) 保证线程安全。因为数据结构是链表，所以理论上是没有队列大小限制的，也就是说添加数据一定能成功。
* ConcurrentLinkedQueue的HOPS(延迟更新的策略):
*
* */
public class ConcurrentLinkedQueueDemo {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
        queue.add(13);
        queue.add(19);
        queue.add(5);
        queue.add(4);
        queue.add(13);

        //在队列末端添加一个节点
        queue.offer(9);

        //获取头部的这个节点(会从队列中移除)，如果队列为空则返回null；
        Integer head = queue.poll();
        System.out.println(head);
        //获取头部的这个节点，但不移除
        head = queue.peek();
        System.out.println(head);
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()){
            Integer item = iterator.next();
            System.out.print(item +",") ;
        }

    }

}
