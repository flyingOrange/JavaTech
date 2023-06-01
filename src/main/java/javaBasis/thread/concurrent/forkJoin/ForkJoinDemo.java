package javaBasis.thread.concurrent.forkJoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinDemo {
	public static void main(String[] args) {
		final int SIZE = 1000000;
		double []numbers = new double[SIZE];
		for (int i = 0; i < SIZE; i++)
			numbers[i] = Math.random();
		Counter counter = new Counter(numbers, 0, numbers.length, x -> x > 0.5);
		// ForkJoin线程池
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(counter);
		//System.out.println(counter.join);
	}
}
