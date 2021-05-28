package designPattern.singleton;

//双重检查DCL(double check lock)
public class Person4 {
	/*
	* 注意此处要加volatile,因为存在指令重排序问题，返回一个半初始化对象。
	* */
	private static volatile Person4 person;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//私有构造方法
	private Person4(){}
	//只有首次创建对象的时候需要同步访问
	public static Person4 getPerson() {
		if(person == null) {
			synchronized(Person4.class) {
				//双重检查
				if(person == null)
					return new Person4();//volatile禁止指令重排
			}
		}
		return person;
	}

}