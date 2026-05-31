package stream_api.unit5.threading_concurency.part3;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Main1 {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.submit(() -> System.out.println(Thread.currentThread().getName() + " -> Task " + taskId));
        }

        //executor.shutdown();
        //System.out.println("//----------------------------------------//");
        //futureDemo();
        //System.out.println("//----------------------------------------//");
        //compareStreams();
        System.out.println("//----------------------------------------//");
        customPoolDemo();


    }

    public static void futureDemo() throws Exception {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        Callable<Integer> task = () -> {
            System.out.println("Calculating...");
            Thread.sleep(1000);
            return 42;
        };

        Future<Integer> result = exec.submit(task);
        System.out.println("Result " + result.get());
        exec.shutdown();

    }

    public static void compareStreams() {
        List<Integer> data = IntStream.rangeClosed(1, 10_000_000).boxed().toList();
        long t1 = System.currentTimeMillis();
        data.stream().map(x -> x * x).toList();
        long t2 = System.currentTimeMillis();
        data.parallelStream().map(x -> x * x).toList();
        long t3 = System.currentTimeMillis();
        System.out.println("stream " + (t2 - t1) + " ms");
        System.out.println("parallelStream " + (t3 - t2) + " ms");
    }


    public static void customPoolDemo() throws ExecutionException, InterruptedException {
        ForkJoinPool customPool = new ForkJoinPool(2);
        customPool.submit(() -> {
            IntStream.rangeClosed(1, 10)
                    .parallel()
                    .forEach(i -> System.out.println(Thread
                            .currentThread()
                            .getName() + " -> " + i)
                    );
        }).get();
        customPool.shutdown();
    }


    public void completableDemo() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task1");
            sleep(500);
            return 10;
        });

        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task2");
            sleep(300);
            return 20;
        });

        CompletableFuture<Integer> result = task1.thenCombine(task2, Integer::sum);
        System.out.println("Result " + result.get());

    }

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
