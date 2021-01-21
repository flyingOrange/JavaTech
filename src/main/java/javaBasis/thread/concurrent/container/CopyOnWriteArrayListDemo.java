package javaBasis.thread.concurrent.container;

import java.util.concurrent.CopyOnWriteArrayList;

/*
并发版 ArrayList，底层结构也是数组，和 ArrayList 不同之处在于：当新增和删除元素时会创建一个新的数组，
在新的数组中增加或者排除指定对象，最后用新增数组替换原来的数组。

适用场景：由于读操作不加锁，写（增、删、改）操作加锁，因此适用于读多写少的场景。
局限：由于读的时候不会加锁（读的效率高，就和普通 ArrayList 一样），读取的当前副本，因此可能读取到脏数据。如果介意，建议不用。
* */
public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<Integer>();
        Integer i = 4;
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        list.addIfAbsent(i);
        System.out.println(list);

    }

}
