package designPattern.state.v3;

public class SupperState extends State {

	@Override
	public void doSomething(Person person) {
		if(person.getHour() == 18) {
			System.out.println("吃早饭");
		}else {
			person.setState(new NoState());
			person.doSomething();
		}
	}
}
