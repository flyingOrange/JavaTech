package javaBasis.thread.concurrent.container;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/*
并发 Set
基于 CopyOnWriteArrayList 实现（内含一个 CopyOnWriteArrayList 成员变量），
也就是说底层是一个数组，意味着每次 add 都要遍历整个集合才能知道是否存在，不存在时需要插入（加锁）。

适用场景：在 CopyOnWriteArrayList 适用场景下加一个，集合别太大（全部遍历伤不起）。
该集合类的迭代器不支持remove（）操作，尝试在迭代时删除一个元素将导致UnSupportedOperationException
* */
public class CopyOnWriteArraySetDemo {
    public static void main(String[] args) {
        CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<Integer>();
        set.add(43);
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            Integer num = it.next();
            System.out.println(num);
        }

    }
}
