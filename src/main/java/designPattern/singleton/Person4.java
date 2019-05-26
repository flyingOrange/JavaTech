package designPattern.singleton;

//双重检查
public class Person4 {
	private static Person4 person;
	
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
					return new Person4();
			}
		}
		return person;
	}

}