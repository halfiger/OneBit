package stream_api.unit2.stream15_grouping;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main1 {
    public Map<String, Boolean> task1(List<String> list) {
        return list.stream().collect(Collectors.toMap(Function.identity(), this::hasVowels));
    }

    public boolean hasVowels(String s) {
        return s.chars().filter(c -> "aeiou".indexOf(c) >= 0).findAny().isPresent();
    }

    public long task2(String s) {
        Map<Character, Long> map = s.toLowerCase().chars().mapToObj(c -> (char) c)
                .collect(Collectors
                        .groupingBy(Function.identity(), Collectors.counting()));
        return map.entrySet().stream().filter(e -> e.getValue() > 1).count();
    }

    public String task3(String s) {
        return Arrays.stream(s.split("//s*"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("");
    }

    public Map<Integer, Integer> task4(List<Integer> list) {
        return list.stream().collect(Collectors.groupingBy(a -> a % 10, Collectors.summingInt(n -> n)));
    }

    public Map<Integer, Long> task5(List<String> list) {
        return list.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
    }

    public Map<Integer, Long> task6(String s) {
        return s.chars()
                .filter(Character::isDigit)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Boolean task7 (String [] array ) {
        return Arrays.stream(array).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().anyMatch(entry -> entry.getValue() > 1);
    }

    public Map <String, List<Integer>> task8 (Integer [] array) {
        return Arrays.stream(array).collect(Collectors.groupingBy(n->n%2 == 0 ? "even" : "odd"));
    }
}
