package designPattern.singleton;

//懒汉模式
public class Person2 {
	private static Person2 person;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//私有构造方法
	private Person2(){}
	//此时在多线程时会出现创建多个Person对象的情况
	public static Person2 getPerson() {
		if(person == null) {
			return new Person2();
		}
		return person;
	}

}