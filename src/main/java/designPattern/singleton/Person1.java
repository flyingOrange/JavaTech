package designPattern.singleton;

//饿汉模式(创建类的唯一实例，使用private static修饰)
public class Person1 {
	private static final Person1 person = new Person1();
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//私有构造方法
	private Person1(){}
	//全局静态方法
	public static Person1 getPerson() {
		return person;
	}

}
