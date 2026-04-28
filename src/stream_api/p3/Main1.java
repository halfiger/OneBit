package stream_api.p3;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main1 {
    String [] words = new String[] {"Aa", "Bba", "Cec"};
    public Map<String, Boolean> task1 () {
        return Arrays.stream(words)
                .collect(Collectors.toMap(Function.identity(),
                        w->w.chars().anyMatch(c->"aeiou".indexOf(c)>=0)));
    }

    public Map <Boolean, List<String>> task2 (List <String> list) {
        return list.stream().collect(Collectors
                .partitioningBy(w->w.chars().anyMatch(c->"aeiou".indexOf(c)>=0)));
    }

    public Map <String, Long> task3 (List <String> list) {
        return list.stream().filter(w->w.chars().anyMatch(c->"aeiou".indexOf(c)>=0))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Map <Character, Double> task4 () {
        return Arrays.stream(words).collect(Collectors.groupingBy(w->w.charAt(0), Collectors.averagingInt(String::length)));
    }

    public Character task5 () {
        return Arrays.stream(words).collect(Collectors.groupingBy(w->w.charAt(0), Collectors.counting()))
                .entrySet().stream().max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse('-');
    }

    public Map <String, Long> task6 () {
        return Arrays.stream(words).map(String::toUpperCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public IntSummaryStatistics task7 () {
        return Arrays.stream(words).filter(w->countLetters(w) == 0).mapToInt(String::length).summaryStatistics();
    }

    public Long countLetters(String w) {
        return w.length() - w.chars().distinct().count();
    }

    public Map <Boolean, List<String>> task8 () {
        return Arrays.stream(words).collect(Collectors.partitioningBy(w->Character.isUpperCase(w.charAt(0))));
    }

    public String task9 () {
        return Arrays.stream(words).filter(w->Character.isUpperCase(w.charAt(0)))
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }
}
