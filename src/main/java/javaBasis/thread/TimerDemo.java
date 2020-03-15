package javaBasis.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/*
 * 
void cancel()    终止此计时器，丢弃所有当前已安排的任务。
int purge()     从此计时器的任务队列中移除所有已取消的任务。
void schedule(TimerTask task, Date time)     安排在指定的时间执行指定的任务。
void schedule(TimerTask task, Date firstTime, long period)     安排指定的任务在指定的时间开始进行重复的固定延迟执行。
void schedule(TimerTask task, long delay)     安排在指定延迟后执行指定的任务。
void schedule(TimerTask task, long delay, long period)     安排指定的任务从指定的延迟后开始进行重复的固定延迟执行。
void scheduleAtFixedRate(TimerTask task, Date firstTime, long period)     安排指定的任务在指定的时间开始进行重复的固定速率执行。
void scheduleAtFixedRate(TimerTask task, long delay, long period)     安排指定的任务在指定的延迟后开始进行重复的固定速率执行。
 * */
public class TimerDemo {
	
	private static Timer timer = new Timer(true);
	
	static public class Task extends TimerTask{
		@Override
		public void run() {
			System.out.println("运行,时间为:"+ new Date());
		}
	}
	
	public static void main(String[] args) {
		Task task = new Task();
		//2020-01-14 07:12:45
		Date d1 = new Date(1578957165000l);
		Date d2 = new Date();
		System.out.println("计划时间:"+d1+",当前时间:"+d2);
		
		Date d3 = new Date(d2.getTime()+5*1000);
		
		timer.schedule(task, d3);
		
		
	}
	
}
