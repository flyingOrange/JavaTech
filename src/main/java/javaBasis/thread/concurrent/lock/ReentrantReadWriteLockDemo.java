package javaBasis.thread.concurrent.lock;

import java.util.Date;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) throws InterruptedException {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        Thread thread0 = new Thread(() -> {
            writeLock.lock();
            try {
                Thread.sleep(3000);
                System.out.println("Thread 1 running " + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        },"t1");

        Thread thread1 = new Thread(() -> {
            writeLock.lock();
            try {
                Thread.sleep(3000);
                System.out.println("Thread 2 running " + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        },"t2");

        thread0.start();
        thread1.start();

        thread0.join();
        thread1.join();

    }

}
