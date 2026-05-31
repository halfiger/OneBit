package stream_api.unit3.task9_;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main1 {
    public void practice1 () {
        List.of(1,2,3,4,5)
                .stream()
                .forEach(System.out::println);
    }

    public void practice2 () {
        List.of(1,2,3,4,5)
                .parallelStream()
                .forEach(System.out::println);
    }

    public void practice3 () {
        List.of(1,2,3,4,5)
                .parallelStream()
                .forEachOrdered(System.out::println);
    }

    public void practice4 () {
        List.of("A", "B", "C","D")
                .parallelStream()
                .forEach(s-> System.out.println(Thread.currentThread().getName() + "->" + s));
    }

    public void practice5 () {
        List <Integer> nums = IntStream.range(0,1_000_000).boxed().toList();
        long t1 = System.currentTimeMillis();
        nums.stream().map(Math::sqrt).toList();
        long t2 = System.currentTimeMillis();

        nums.parallelStream().map(Math::sqrt).toList();
        long t3 = System.currentTimeMillis();

        System.out.println("Normal " + (t2 -t1) + "ms");
        System.out.println("Normal " + (t3 -t2) + "ms");
    }

    public void practice6 () {
        List <Integer> result = new ArrayList<>();
        IntStream.range(1,10).parallel().forEach(result::add);
        System.out.println(result);// Може бути неповним або кинути Exception
    }

    public void practice7 () {
        List <Integer> result = IntStream.range(1,10)
                .parallel()
                .boxed()
                .toList();
        System.out.println(result);
    }

    public void practice8 () {
        List.of("a", "b", "c", "d")
                .parallelStream()
                .peek(s-> System.out.println("peek " + s))
                .forEach(System.out::println);

        System.out.println("----------");

        List.of("a", "b", "c", "d")
                .parallelStream()
                .peek(s->System.out.println("peek " + s))
                .forEachOrdered(System.out::println);
    }

    public void practice9 () {
        int sum = IntStream.rangeClosed(1, 5)
                .parallel()
                .reduce(0, Integer::sum);
        System.out.println(sum);
    }

    public void practice10 () {
        int result = IntStream.rangeClosed(1,5)
                .parallel()
                .reduce(0, (a,b) -> a-b);
        System.out.println(result);
    }


}
