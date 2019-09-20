package designPattern.memento;
/*
 * 备忘录模式：
 * 它的作用是保存对象的内部状态，并在需要的时候回复对象以前的状态。
 * People原生者，Memento备忘录，CareTaker管理者
 * 
 * */
public class MainClass {
	
	public static void main(String[] args) {
		People people = new People("zhangsan","男",30);
		//保存内部状态
		People backup = new People();
		backup.setName(people.getName());
		backup.setSex(people.getSex());
		backup.setAge(people.getAge());
		System.out.println(people);
		//修改
		people.setAge(20);
		System.out.println(people);
		//回滚、还原
		people.setAge(backup.getAge());
		System.out.println(people);
		
		
		/******使用备忘录模式******/
		Memento memento = people.createMemento();
		CareTaker careTaker = new CareTaker();
		careTaker.setMemento(memento);
		//修改
		people.setName("sss");
		people.setSex("女");
		people.setAge(13);
		//回复还原
		people.setMemento(careTaker.getMemento());
		
	}

}
