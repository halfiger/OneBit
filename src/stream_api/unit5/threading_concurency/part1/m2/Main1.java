package stream_api.unit5.threading_concurency.part1.m2;

public class Main1 {

    public void createMessage () {
        Runnable job = () -> {
            for (int i=0; i<5; i++) {
                System.out.println("message # " + i + " from " + Thread.currentThread().getName());
            }
        };
        Thread t = new Thread(job);
        t.start();
        t.run();
    }

    public static void main(String[] args) {
        Main1 main1 = new Main1();
        main1.createMessage();
    }
}
