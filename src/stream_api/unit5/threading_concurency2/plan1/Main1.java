package stream_api.unit5.threading_concurency2.plan1;

import java.util.Set;

public class Main1 {

    public static void main(String[] args) throws InterruptedException {
        Main1 main1 = new Main1();
        main1.task5();
    }

    public void task1() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A");
                try {
                    Thread.sleep((int) Math.random() * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("B");
                try {
                    Thread.sleep((int) Math.random() * 50);
                } catch (InterruptedException e) {
                }
            }
        });

        Thread t3 = new Thread(() -> {
            System.out.println("Finish");
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        t3.start();
        t3.join();
        System.out.println("done");
    }


    public static int counter = 0;

    public void task2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter++;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter++;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {

                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter);

    }

    public void task3 () {
        Thread t1 = new Thread( ()-> {
            System.out.println("start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        });

        Thread t2 = new Thread( () -> {
            try {
                t1.join();
                System.out.println("middle");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }  );

        Thread t3 = new Thread( () -> {
            try {
                t2.join();
                System.out.println("end");
            } catch (InterruptedException e) {
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }

    public void task4 () throws InterruptedException {
        Thread daemon = new Thread( () -> {
            while(true) {
            System.out.println("♥ heartbeat");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }});
        daemon.setDaemon(true);
        daemon.start();
        Thread.sleep(5000);
        System.out.println("main done");
    }


    public void task5 () {
        Set<Thread> threads = Thread.getAllStackTraces().keySet();
        threads.forEach(t-> System.out.println(t.getName() + " | " + (t.isDaemon() ? "daemon" : "user") + " | " + t.getState()));
    }
}
