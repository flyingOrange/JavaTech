package designPattern.state.v3;

import designPattern.state.v3.State;

public class Person {
	
	private int hour;
	
	private State state;
	
	public Person() {
		state = new BreakfastState();
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	//
	public void doSomething() {
		state.doSomething(this);
		state = new BreakfastState();
	}
}
