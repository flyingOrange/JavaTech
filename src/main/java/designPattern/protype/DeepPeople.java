package designPattern.protype;

import java.util.ArrayList;
import java.util.List;

public class DeepPeople implements Cloneable{
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
	public DeepPeople clone() {
		try {
			DeepPeople people = (DeepPeople) super.clone();
			List<String> newFriends = new ArrayList<String>();
			for(String friend : this.friends) {
				newFriends.add(friend);
			}
			people.setFriends(newFriends);
			return people;
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
