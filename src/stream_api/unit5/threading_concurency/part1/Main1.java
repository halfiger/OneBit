package stream_api.unit5.threading_concurency.part1;

public class Main1 {
    static int counter = 0;

    public static void practice (String [] args) {
        Runnable job = () -> {
            for (int i = 1; i<=5; i++) {
                System.out.println(Thread.currentThread().getName()
                        + " -> message # " + i);
            }
        };

        Thread t = new Thread(job);
        t.start();
        System.out.println("Main thread finished setup");
    }




    public static void practice3 (String [] args ) {
        Runnable job = () -> {

            for (int i = 1; i<=5; i++) {
                System.out.println(Thread.currentThread().getName()
                        + "-> message # " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread t = new Thread(job);
        t.start();
    }

    public static synchronized void main (String [] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i=0; i<1000; i++) {
                counter++;
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Counter = "+counter);
    }










}
