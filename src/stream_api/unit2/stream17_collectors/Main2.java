package stream_api.unit2.stream17_collectors;

import java.util.*;
import java.util.stream.Collectors;

public class Main2 {

    public static List<String> words = List.of("apple", "ant",
            "banana", "bat", "car");

    public static List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

    public Map<Character, List <Integer>> practice1 () {
        return words.stream()
                .collect(Collectors
                        .groupingBy(w->w.charAt(0), Collectors
                                .mapping(String::length, Collectors.toList())));
    }

    public Map <Character, List <String>> practice2 () {
        return words.stream()
                .collect(Collectors
                        .groupingBy(w->w.charAt(0), Collectors
                                .mapping(String::toUpperCase, Collectors.toList())));
    }

    public Map<Boolean, Long> practice3 () {
        return numbers.stream()
                .collect(Collectors
                        .partitioningBy(a->a%3==0, Collectors.counting()));
    }

    public Map <Integer, Set<String>> practice4 () {
        return words.stream()
                .collect(Collectors
                        .groupingBy(String::length, Collectors.toSet()));
    }

    public String practice5 () {
        return numbers.stream()
                .collect(Collectors.collectingAndThen(Collectors
                        .counting(), count -> "count " + count));
    }

    public Map<Character, String> practice6 () {
        return words.stream()
                .collect(Collectors.groupingBy(w->w.charAt(0), Collectors.joining(", ")));
    }

    public Map<Integer, Integer> practice7 () {
        return numbers.stream().collect(Collectors.groupingBy(a->a%3, Collectors.summingInt(n->n)));
    }

    public String practice8 () {
        return words.stream().reduce("", (a, b) -> a.length() > b.length() ? a : b);
    }

    public Map <Character, List <String>> practice9 () {
        return words.stream().collect(Collectors.groupingBy(w->w.charAt(0), Collectors.collectingAndThen(Collectors.toList(), list -> list.stream().sorted(Comparator.comparingInt(String::length)).toList())));
    }

    public TreeMap<Integer, List <String>> practice10 () {
        return words.stream().collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList()));
    }


 }
