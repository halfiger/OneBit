package stream_api.unit5.threading_concurency.part1.m1;

public class Main1 {
    static int counter = 0;
    public static void main(String[] args) throws InterruptedException {
        Runnable job = () -> {
            for (int i = 0; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " → mesasage # " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        Runnable job2 = () -> {
                for (int i = 0; i< 1000; i++) {
                    counter++;
                }
        };

        Thread thread1 = new Thread(job2);
        Thread thread2 = new Thread(job2);


        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(" Counter = " + counter);
    }
}