package java8newFeatures.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamTest {

    @Test
    public void test() {
        // 构造流的几种常见方法
        // 1. Individual values
        Stream stream1 = Stream.of("a", "b", "c");
        // 2. Arrays
        String[] strArray = new String[] { "a", "b", "c" };
        Stream stream2 = Stream.of(strArray);
        Stream stream3 = Arrays.stream(strArray);
        // 3. Collections
        List<String> list = Arrays.asList(strArray);
        Stream stream4 = list.stream();

        // 数值流的构造
        IntStream.of(new int[] { 1, 2, 3 }).forEach(System.out::println);
        IntStream.range(2, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);

        // 流转换为其它数据结构(一个 Stream 只可以使用一次，下面的代码为了简洁而重复使用了数次)
        // 1. Array
        String[] strArray1 = (String[]) stream1.toArray(String[]::new);
        // 2. Collection
        List<String> list1 = (List<String>) stream1.collect(Collectors.toList());
        List<String> list2 = (List<String>) stream1.collect(Collectors.toCollection(ArrayList::new));
        Set set1 = (Set) stream1.collect(Collectors.toSet());
        Stack stack1 = (Stack) stream1.collect(Collectors.toCollection(Stack::new));
        // 3. String
        String str = stream1.collect(Collectors.joining()).toString();

    }

    @Test
    public void operate() {
        /*
         * 流操作归类:
         * Intermediate: 
         * map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、
         * peek、 limit、 skip、 parallel、 sequential、 unordered
         * 
         * Terminal:
         * forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、
         * max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、
         * iterator
         * 
         * Short-circuiting:
         *  anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、
         * limit
         */
        List<Integer> nums = new ArrayList<Integer>() {
            {
                add(1);
                add(null);
                add(3);
                add(4);
                add(null);
                add(6);
            }
        };
        long n = nums.stream().filter(num -> num != null).count();
        System.out.println(n);
        

    }

}
