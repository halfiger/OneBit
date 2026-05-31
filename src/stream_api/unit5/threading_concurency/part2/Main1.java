package stream_api.unit5.threading_concurency.part2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Main1 {

    static int counter = 0;
    static ReentrantLock lock = new ReentrantLock();
    static AtomicInteger counter3 = new AtomicInteger(0);

    static int syncCounter = 0;
    static AtomicInteger atomicCounter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                lock.lock();
                try {
                    counter++;
                } finally {
                    lock.unlock();
                }
            }
        };

        Runnable task2 = () -> {
            if (lock.tryLock()) {
                try {
                    System.out.println(Thread.currentThread().getName() + " got the lock");
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " skipped (lock busy)");
            }
        };

        Runnable task3 = () -> {
            for (int i = 0; i < 1000; i++) {
                counter3.incrementAndGet();
            }
        };
        long start, end;

        Runnable syncTask = () -> {for (int i =0; i<1_000_000; i++) incSync();};
        Runnable atomicTask = () -> {for (int i = 0; i<1_000_000; i++) atomicCounter.incrementAndGet();};



        Thread t1 = new Thread(syncTask);
        Thread t2 = new Thread(syncTask);

        start = System.currentTimeMillis();
        t1.start();t2.start();t1.join();t2.join();
        end = System.currentTimeMillis();
        System.out.println("synchronized " + (end - start) + " ms");

        t1 = new Thread(atomicTask);
        t2 = new Thread(atomicTask);
        start = System.currentTimeMillis();
        t1.start(); t2.start(); t1.join(); t2.join();
        end = System.currentTimeMillis();
        System.out.println("AtomicInteger " + (end - start) + " ms");
    }

    public static synchronized void incSync() {syncCounter++;}




}

