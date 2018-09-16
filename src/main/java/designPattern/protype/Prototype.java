package designPattern.protype;

//原型模式就是讲一个对象作为原型，使用clone()方法来创建新的实例。
//此处使用的是浅拷贝，关于深浅拷贝，大家可以另行查找相关资料。
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
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		} finally {
			return null;
		}
	}
	
	public static void main ( String[] args){
        Prototype pro1 = new Prototype();
        pro1.setName("hahaha");
        System.out.println(pro1);
        
        Prototype pro2 = (Prototype)pro1.clone();
        pro2.setName("gaga");        
        System.out.println(pro1);
        System.out.println(pro2);       
    }

}
