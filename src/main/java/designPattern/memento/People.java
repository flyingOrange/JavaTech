package designPattern.memento;

public class People {
	
	private String name;
	private String sex;
	private int age;
	
	//创建备份
	public Memento createMemento() {
		return new Memento(name,sex,age);
	}
	//回复备份
	public void setMemento(Memento memento) {
		this.name = memento.getName();
		this.sex = memento.getSex();
		this.age = memento.getAge();
	}
	public People() {
	}
	
	public People(String name, String sex, int age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "People [name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}
	
}
