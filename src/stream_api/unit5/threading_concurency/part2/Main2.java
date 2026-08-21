package stream_api.unit5.threading_concurency.part2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Main2 {
    static int counter = 0;
    static ReentrantLock lock = new ReentrantLock();
    static AtomicInteger count = new AtomicInteger(0);
    static int syncCounter = 0;
    static AtomicInteger atomicCounter = new AtomicInteger(0);


    public static void practice1() throws InterruptedException {
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

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    public static void practice7() throws InterruptedException {
        Runnable task = () -> {
            if (lock.tryLock()) {
                try {
                    System.out.println(Thread
                            .currentThread()
                            .getName()
                            + " got the lock");
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " skipped (lock buzy)");
            }
        };

        new Thread(task, "T1").start();
        new Thread(task, "T2").start();
    }

    //static AtomicInteger count = new AtomicInteger(0)
    public static void practice8() throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                count.incrementAndGet();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Count = " + count.get());
    }

    //static int syncCounter = 0;
    //static AtomicInteger atomicCounter = new AtomicInteger(0);


    public static synchronized void incSync() {
        syncCounter++;
    }

    public static void practice9() throws InterruptedException {
        long start, end;

        Runnable syncTask = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                incSync();
            }
        };

        Runnable atomicTask = () -> {
            for (int i = 0; i < 1_000_000; i++) atomicCounter.incrementAndGet();
        };

        Thread t1 = new Thread(syncTask);
        Thread t2 = new Thread(syncTask);
        start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        end = System.currentTimeMillis();
        System.out.println("synchronized " + (end - start) + "ms");

        t1 = new Thread(atomicTask);
        t2 = new Thread(atomicTask);
        start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        end = System.currentTimeMillis();
        System.out.println("Atomic Integer " + (end - start) + "ms");
    }

    //--10--//
    static ReentrantLock lockA = new ReentrantLock();
    static ReentrantLock lockB = new ReentrantLock();

    public static void practice10() {
        Thread t1 = new Thread(() -> {
            lockA.lock();
            System.out.println("T1 locked A");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
            lockB.lock();
            System.out.println("T1 locked B");
        });
        Thread t2 = new Thread(() -> {
            lockB.lock();
            System.out.println("T2 locked B");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
            lockA.lock();
            System.out.println("T2 locked A");
        });
        t1.start();
        t2.start();
    }
}
