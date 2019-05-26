package designPattern.protype;

//原型模式就是讲一个对象作为原型，使用clone()方法来创建新的实例。
public class Prototype implements Cloneable {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Prototype [name=" + name + "]";
	}

	@Override
	public Prototype clone() {
		try {
			return (Prototype) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

}
