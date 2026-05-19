package stream_api.unit2.stream19_sorted;

import java.util.Comparator;
import java.util.List;

public class Main1 {

    public String [] task1 (List<String> list) {
        return list.stream()
                .filter(w->w.length()>3)
                .sorted(Comparator.comparing(String::length)
                        .reversed())
                .toArray(String[]::new);
    }

    public String [] task2 (List <String> list) {
        return list.stream().map(String::toLowerCase)
                .sorted()
                .toArray(String[]::new);
    }

    public Integer [] task3 (List <Integer> list) {
        return list.stream().sorted(Comparator.reverseOrder())
                .toArray(Integer[]::new);
    }

    public Integer [] task5 (List <Integer> list) {
        return list.stream().sorted(Comparator.comparing(n->(int)n%2).thenComparing(n->(int)n))
                .toArray(Integer[]::new);
    }
}
