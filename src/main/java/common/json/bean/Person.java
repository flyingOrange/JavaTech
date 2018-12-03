package common.json.bean;

import java.util.List;

public class Person {
	
	String name;
	
	int age;
	
	List<Son> sons;

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

	public List<Son> getSons() {
		return sons;
	}

	public void setSons(List<Son> sons) {
		this.sons = sons;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", sons=" + sons + "]";
	}

}
