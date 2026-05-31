package stream_api.unit3.task2_;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main1 {
public void practice1 () {
    IntStream.range(1,5).forEach(System.out::println);
    IntStream.rangeClosed(1,5).forEach(System.out::println);
}

public void practice2 () {
    int sum = IntStream.rangeClosed(1,5).map(n->n*n).sum();
    System.out.println(sum);
}

public void practice3 () {
    DoubleStream.generate(Math::random)
            .limit(5)
            .forEach(System.out::println);
}

public void practice4 () {
    int result = DoubleStream.of(1.2, 2.5, 3.8)
            .mapToInt(d->(int) Math.round(d))
            .sum();
    System.out.println(result);
}

public void practice5 () {
    long factorial = LongStream.rangeClosed(1,5)
            .reduce(1, (a,b)->a*b);
    System.out.println(factorial);
}

public void practice6 () {
    OptionalDouble avg = IntStream.of(10, 20, 30, 40).average();
    System.out.println(avg.getAsDouble());
}

public void practice7 () {
    List<String> list = IntStream.rangeClosed(1,3)
            .mapToObj(i-> "Num " + i).toList();
    System.out.println(list);
}

    public void practice8 () {
        List <Integer> list = DoubleStream.generate(Math::random)
                .limit(5)
                .map(d->d*10)
                .mapToInt(d->(int) Math.round(d))
                .boxed()
                .toList();
        System.out.println(list);
    }

    public void practice9 () {
        IntSummaryStatistics stats = IntStream.of(2, 4, 6 ,8 , 10)
                .summaryStatistics();
        System.out.println(stats.getAverage());
        System.out.println(stats.getCount());
    }

    public void practice10 () {
        DoubleStream.generate(() -> Math.random() * 100)
                .limit(10)
                .mapToLong(Math::round)
                .asDoubleStream()
                .average()
                .orElse(0);
    }

 }
