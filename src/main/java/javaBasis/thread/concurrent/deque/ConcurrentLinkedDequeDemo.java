package javaBasis.thread.concurrent.deque;

import java.util.concurrent.ConcurrentLinkedDeque;

/*
*基于链表的无界的同时支持FIFO、LIFO的非阻塞并发双端队列,使用CAS实现并发安全.
* 可以分别对头尾进行操作，因此除了先进先出 (FIFO)，也可以先进后出（FILO），当然先进后出的话应该叫它栈了。
* 是链表，所以理论上是没有队列大小限制的，也就是说添加数据一定能成功。
* */
public class ConcurrentLinkedDequeDemo {
    public static void main(String[] args) {
        ConcurrentLinkedDeque deque = new ConcurrentLinkedDeque();


    }
}
