package designPattern.state.v2;

import designPattern.state.v2.BreakfastState;
import designPattern.state.v2.LunchState;
import designPattern.state.v2.State;
import designPattern.state.v2.SupperState;

public class Person {
	
	private int hour;
	
	private State state;

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}
	
	//使用状态模式的做法
	public void doSomething() {
		if(hour == 7) {
			state = new BreakfastState();
			state.doSomething();
		}
		else if(hour == 12) {
			state = new LunchState();
			state.doSomething();
		}
		else if(hour == 18) {
			state = new SupperState();
			state.doSomething();
		}else{
			System.out.println("未定义");
		}
	}

}
