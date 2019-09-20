package designPattern.state.v3;

public class LunchState extends State{
	
	@Override
	public void doSomething(Person person) {
		if(person.getHour() == 12) {
			System.out.println("吃午饭");
		}else {
			person.setState(new SupperState());
			person.doSomething();
		}
	}

}
