package common.json.bean;

import java.util.Date;

public class Son {
	String name;
	
	Date birth;
	
	public Son(String name,Date birth) {
		this.name = name;
		this.birth = birth;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "Son [name=" + name + ", birth=" + birth + "]";
	}
	
}
