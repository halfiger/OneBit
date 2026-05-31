package stream_api.unit5.threading_concurency2.plan3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main1 {
    Lock lockA = new ReentrantLock();

    private int counter =0;
    private final ReentrantLock lock = new ReentrantLock();
    void increment () {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }

    int getCount() {
        return counter;
    }
}