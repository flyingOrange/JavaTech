package designPattern.state.v3;

public class BreakfastState extends State{

	@Override
	public void doSomething(Person person) {
		if(person.getHour() == 7) {
			System.out.println("吃早饭");
		}else {
			person.setState(new LunchState());
			person.doSomething();
		}
		
	}

}
