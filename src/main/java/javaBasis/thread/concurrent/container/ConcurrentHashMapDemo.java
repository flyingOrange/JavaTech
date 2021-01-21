package javaBasis.thread.concurrent.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
*  并发版 HashMap
最常见的并发容器之一，可以用作并发场景下的缓存。底层依然是哈希表，但在 JAVA 8 中有了不小的改变，
* 而 JAVA 7 和 JAVA 8 都是用的比较多的版本，因此经常会将这两个版本的实现方式做一些比较（比如面试中）。

一个比较大的差异就是，JAVA 7 中采用分段锁来减少锁的竞争，JAVA 8 中放弃了分段锁，采用 CAS（一种乐观锁），
同时为了防止哈希冲突严重时退化成链表（冲突时会在该位置生成一个链表，哈希值相同的对象就链在一起），
会在链表长度达到阈值（8）后转换成红黑树（比起链表，树的查询效率更稳定）。
* */
public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        Map<Long, String> conMap = new ConcurrentHashMap<Long, String>();
        conMap.put(32l,"casda");
        conMap.put(32l,"rtr");
        conMap.put(32l,"uuff");

        System.out.println(conMap);
    }


}
