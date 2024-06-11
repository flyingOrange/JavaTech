package javaBasis.java8newFeatures.methodReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.junit.Test;

//lambda表达式用于替换函数式接口，方法引用也是如此，方法引用可以使代码更加简单和便捷
//1.方法引用所引用的方法的参数列表必须要和函数式接口中抽象方法的参数列表相同（完全一致）
//2.方法引用所引用的方法的的返回值必须要和函数式接口中抽象方法的返回值相同（完全一致）

//jdk提供的常用函数式接口:Consumer   Supplier  Predicate

/*
 * 方法引用主要有如下三种使用情况 （1）. 类：：实例方法 （2）. 类：：静态方法 （3）. 对象：：实例方法
 * 前两种方式类似，等同于把lambda表达式的参数直接当成instanceMethod|staticMethod的参数来调用。比如System.out::
 * println等同于x->System.out.println(x)；Math::max等同于(x, y)->Math.max(x,y)。
 * 
 * 最后一种方式，等同于把lambda表达式的第一个参数当成instanceMethod的目标对象，其他剩余参数当成该方法的参数。比如String::
 * toLowerCase等同于x->x.toLowerCase()。
 */
public class MethodReference {

	@Test
	public void test() {
		List<String> strLst = new ArrayList<String>() {
			{
				add("aa");
				add("bbbbbbbb");
				add("cccccccccccccccc");
			}
		};
		Collections.sort(strLst, String::compareToIgnoreCase);
		System.out.println(strLst);
	}
	
	//类名::实例方法名
	@Test
	public void test1() {
	  //传统Lambda表达式
        BiPredicate<String,String> biPredicate = (x,y) -> x.equals(y);
        boolean test = biPredicate.test("hello", "hi");
        System.out.println(test);//false

        //方法引用
        biPredicate = String::equals;
        test = biPredicate.test("hello", "hello");
        System.out.println(test);//true
	}
	
	//类名::静态方法名
	@Test
	public void test2() {
	  //传统Lambda表达式
        Consumer<String> consumer = (str) -> test(str);
        consumer.accept("Hello : XiangYang"); //打印：Hello : XiangYang

        //方法引用方式
        consumer = MethodReference::test;
        consumer.accept("Hello : XiangYang"); //打印：Hello : XiangYang
	}

	//实例对象名::实例方法名
	@Test
    public void test3() {
	    Student student = new Student("XiangYang",23);
	    
        Supplier<String> supplier2 = ()->student.getName();
        System.out.println("Lambda形式： "+supplier2.get());

        Supplier<String> supplier1 = student :: getName;
        System.out.println("方法引用形式： "+supplier1.get());
        
        ///////////////////////////////////////////////
        //传统Lambda表达式
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("Hi: 我是Lambda表达式实现的！"); //打印：Hi: 我是Lambda表达式实现的！
        //方法引用实现
        consumer = System.out::println;
        consumer.accept("Hello : XiangYang，我是使用方法引用实现的 ");//打印：Hello : XiangYang，我是使用方法引用实现的 
	}


	public static void test(String str) {
	    System.out.println("test:"+str);
	}
}

class Student{
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(int age) {
        this.age = age;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

