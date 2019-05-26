package designPattern.protype;

import java.util.ArrayList;
import java.util.List;

public class MainClass {
	public static void main(String[] args) {
		//克隆的一般使用
		Prototype pro1 = new Prototype();
		pro1.setName("hahaha");
		System.out.println(pro1);
		Prototype pro2 = pro1.clone();
		System.out.println(pro2);
		System.out.println("--------------------------------------");
		//潜拷贝
		List<String> friends = new ArrayList<String>() {
			{
				this.add("lily");
				this.add("jenny");
				this.add("bill");
			}
		};
		People p1 = new People();
		p1.setName("kkk");
		p1.setFriends(friends);
		People p2 = p1.clone();
		System.out.println(p1);
		System.out.println(p2);		
		//克隆时只复制了friends的引用地址，所以p1、p2共用一个friends引用，这是--潜克隆
		friends.add("lilei");
		System.out.println(p1);
		System.out.println(p2);	
		System.out.println("--------------------------------------");	
		//深拷贝
		List<String> newFriends = new ArrayList<String>() {
			{
				this.add("11111");
				this.add("22222");
				this.add("3333");
			}
		};
		DeepPeople deepp1 = new DeepPeople();
		deepp1.setName("deep People");
		deepp1.setFriends(newFriends);
		DeepPeople deepp2 = deepp1.clone();
		System.out.println(deepp1);
		System.out.println(deepp2);	
		newFriends.add("lilei");
		System.out.println(deepp1);
		System.out.println(deepp2);	
		
	}
}
