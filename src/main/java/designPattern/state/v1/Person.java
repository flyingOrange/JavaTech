package designPattern.state.v1;

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
	
	//不使用状态模式的做法
	public void doSomething() {
		if(hour == 7) {
			System.out.println("吃早饭");
		}
		else if(hour == 12) {
			System.out.println("吃午饭");
		}
		else if(hour == 18) {
			System.out.println("吃晚饭");
		}else{
			System.out.println("未定义");
		}
	}

}
