package javaBasis.generic;

import org.junit.Test;
//泛型有三种使用方式，分别为：泛型类、泛型接口、泛型方法

public class TestCase {

	@Test
	public void GenericClass2() {
		//String 已实现了 Comparable 和 Serializable接口
		GenericClass2<String, Integer> gc2 = new GenericClass2<String, Integer>(
				"hello world", 1234);
		System.out.println(gc2.getKey());
		System.out.println(gc2.getCode());
		gc2.say();
	}

	// @Test
	public void GenericMethod() {
		System.out.println(GenericMethod.getMiddle(1, 2, 3, 4, 5, 6, 7));
	}

	// @Test
	public void GenericWildcard() {
		GenericClass<Integer> integer = new GenericClass<Integer>(123);
		GenericClass<Number> number = new GenericClass<Number>(456);
		// GenericClass<Integer>不能被看作为GenericClass<Number>的子类
		// GenericWildcard.showKeyValue1(integer);
		GenericWildcard.showKeyValue1(number);

		// 使用通配符
		GenericWildcard.showKeyValue2(integer);
		GenericWildcard.showKeyValue2(number);

	}

	// @Test
	public void GenericInterface() {

		FruitGenerator fg = new FruitGenerator();
		System.out.println(fg.next());

	}

	// @Test
	public void GenericClass() {
		GenericClass<Integer> gc = new GenericClass<Integer>(111);
		System.out.println(gc.getKey());
		gc.say();

	}
}
