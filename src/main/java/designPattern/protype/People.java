package designPattern.protype;

import java.util.List;

public class People implements Cloneable{
	
	String name;
	List<String> friends;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getFriends() {
		return friends;
	}
	public void setFriends(List<String> friends) {
		this.friends = friends;
	}
	@Override
	public People clone() {
		try {
			return (People) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public String toString() {
		return "People [name=" + name + ", friends=" + friends + "]";
	}

}
