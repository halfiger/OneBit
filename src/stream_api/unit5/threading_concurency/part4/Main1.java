package stream_api.unit5.threading_concurency.part4;

import java.util.concurrent.CompletableFuture;

public class Main1 {
    public static void main(String[] args) throws Exception
    {
        CompletableFuture <String> cf = CompletableFuture.supplyAsync(
                ()->{
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "Hello, Async World!";
                } );
    }


}
