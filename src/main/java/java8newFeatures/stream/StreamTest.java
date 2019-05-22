package java8newFeatures.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;
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
		 * 流操作归类: Intermediate: map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、
		 * peek、 limit、 skip、 parallel、 sequential、 unordered
		 * 
		 * Terminal: forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、
		 * anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
		 * 
		 * Short-circuiting: anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
		 */

		/*
		 * filter() 下列代码将输出为长度等于3的字符串you和too。注意，由于filter()是个中间操作，如果只调用filter()不会有实际计算，
		 * 因此也不会输出任何信息
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
		nums.stream().filter(num -> {
			if (num == null)
				return false;
			if (num == 4)
				return false;
			return true;
		}).forEach(num -> System.out.println(num));

		/*
		 * distinct() 通过流所生成元素的 hashCode() 和 equals() 去除重复元素
		 */
		List<Student> students = new ArrayList<Student>() {
			{
				add(new Student(1, "zhangsan", 77.5, "物理"));
				add(new Student(2, "lisi", 70.5, "物理"));
				add(new Student(3, "wangwu", 71.5, "物理"));
				add(new Student(1, "zhaoliu", 72.5, "物理"));
			}
		};
		students.stream().distinct().forEach(student -> System.out.println(student));

		/*
		 * sorted() 排序函数有两个，一个是用自然顺序排序，一个是使用自定义比较器排序，函数原型分别为Stream<T> sorted()和Stream<T>
		 * sorted(Comparator<? super T> comparator)
		 */
		Stream<String> stream = Stream.of("I", "love", "you", "too");
		stream.sorted((str1, str2) -> str1.length() - str2.length()).forEach(str -> System.out.println(str));

		/*
		 * map() 作用是返回一个对当前所有元素执行执行mapper之后的结果组成的Stream。 直观的说，就是对每个元素按照某种操作进行转换，
		 * 转换前后Stream中元素的个数不会改变，但元素的类型取决于转换之后的类型。
		 */
		Stream<String> stream1 = Stream.of("I", "love", "you", "too");
		stream1.map(str -> str.toUpperCase()).forEach(str -> System.out.println(str));

		/*
		 * flatMap()
		 * 
		 */
		Stream<List<Integer>> stream2 = Stream.of(Arrays.asList(11, 22), Arrays.asList(33, 44, 55));
		stream2.flatMap(list -> list.stream()).forEach(i -> System.out.println(i));

	}

	/*
	 * reduce()最常用的场景就是从一堆值中生成一个值
	 * reduce操作可以实现从一组元素中生成一个值，sum()、max()、min()、count()等都是reduce操作， 将他们单独设为函数只是因为常用
	 * 
	 * reduce()的方法定义有三种重写形式： Optional<T> reduce(BinaryOperator<T> accumulator) T
	 * reduce(T identity,BinaryOperator<T> accumulator) <U> U reduce(U identity,
	 * BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)
	 * 
	 */
	@Test
	public void reduce() {
		Stream<Integer> stream = Arrays.stream(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8 });
		// 求集合元素只和
		Integer result = stream.reduce(0, Integer::sum);
		System.out.println(result);

		// 求和
		stream = Arrays.stream(new Integer[] { 1, 2, 3, 4, 5, 6, 7 });
		stream.reduce((i, j) -> i + j).ifPresent(System.out::println);

		// 求最大值
		stream = Arrays.stream(new Integer[] { 1, 2, 3, 4, 5, 6, 7 });
		stream.reduce(Integer::max).ifPresent(System.out::println);

		// 求最小值
		stream = Arrays.stream(new Integer[] { 1, 2, 3, 4, 5, 6, 7 });
		stream.reduce(Integer::min).ifPresent(System.out::println);

	}

	/*
	 * 
	 * 
	 * */
	@Test
	public void collect() {
		// 将Stream转换成容器或list,set,map
		Stream<String> stream = Stream.of("II", "lovelove", "youyou", "tootoo");
		List<String> list = stream.collect(Collectors.toList());
		System.out.println(list);
		Stream<String> stream2 = Stream.of("III", "lovelovelove", "youyouyou", "tootootoo");
		Set<String> set = stream2.collect(Collectors.toSet());
		System.out.println(set);
		Stream<String> stream3 = Stream.of("I", "love", "you", "too");
		Map<String, Integer> map = stream3.collect(Collectors.toMap(Function.identity(), String::length));
		System.out.println(map);

		List<Student> result3 = new ArrayList<Student>() {
			{
				add(new Student(1, "zhangsan", 77.5, "物理"));
				add(new Student(2, "lisi", 70.5, "化学"));
				add(new Student(3, "wangwu", 71.5, "物理"));
			}
		};
		Map<Integer, Student> hashMap = result3.stream().collect(Collectors.toMap(Student::getId, student -> student));
		System.out.println(hashMap);
		System.out.println("=======================排序==================================");
		result3.sort((ele1, ele2) -> ele2.getId() - ele1.getId());
		System.out.println(result3);
		System.out.println("=======================分组==================================");
		Map<String, List<Student>> map1 = result3.stream().collect(Collectors.groupingBy(Student::getMajor));
		System.out.println(map1);
		Map<String, List<String>> map2 = result3.stream().collect(
				Collectors.groupingBy(Student::getMajor, Collectors.mapping(Student::getName, Collectors.toList())));
		System.out.println(map2);

		Map<String, Long> map3 = result3.stream()
				.collect(Collectors.groupingBy(Student::getMajor, Collectors.counting()));
		System.out.println(map3);

		// 使用toCollection()指定规约容器的类型
		// 由于返回结果是接口类型，我们并不知道类库实际选择的容器类型是什么，有时候我们可能会想要人为指定容器的实际类型，
		// 这个需求可通过Collectors.toCollection(Supplier<C>collectionFactory)方法完成
		Stream<String> stream6 = Stream.of("I", "love", "you", "too");
		Stream<String> stream7 = Stream.of("I", "love", "you", "too");
		ArrayList<String> arrayList = stream6.collect(Collectors.toCollection(ArrayList::new));
		HashSet<String> hashSet = stream7.collect(Collectors.toCollection(HashSet::new));

	}

}

class Student {
	private int id;
	private String name;
	private Double score;
	private String major;

	public Student(int id, String name, Double score, String major) {
		this.id = id;
		this.name = name;
		this.score = score;
		this.major = major;
	}

	public String getName() {
		return name;
	}

	public Double getScore() {
		return score;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", score=" + score + ", major=" + major + "]";
	}

	@Override
	public boolean equals(Object obj) {
		Student other = (Student) obj;
		if (this.id != other.getId())
			return false;
		return true;
	}

}
