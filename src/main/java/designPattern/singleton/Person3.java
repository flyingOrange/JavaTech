package designPattern.singleton;

//懒汉模式
public class Person3 {
	private static Person3 person;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//私有构造方法
	private Person3(){}
	//synchronized解决了多线程访问的问题
	public static synchronized Person3 getPerson() {
		if(person == null) {
			return new Person3();
		}
		return person;
	}

}