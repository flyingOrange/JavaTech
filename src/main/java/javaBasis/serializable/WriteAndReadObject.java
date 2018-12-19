package javaBasis.serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *把一个对象写入硬盘，体验java的序列化 和 反序列化
 */
class Person implements java.io.Serializable
{
	private String name;
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
	private int age;
	public Person(String name,int age)
	{
		this.name = name;
		this.age = age;
	}
	
}
public class WriteAndReadObject {

	
	public static void main(String[] args) {
		Person per = new Person("orange",28);
		//序列化
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"));
			oos.writeObject(per);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//反序列化
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"));
			Person p = (Person) ois.readObject();
			System.out.println(p.getName());
			System.out.println(p.getAge());
		} catch (FileNotFoundException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
