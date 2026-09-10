package stream_api.unit2.stream17_collectors;

import java.util.*;
import java.util.stream.Collectors;

public class Main1 {

    public Map<Character, List<String>> practice1 (List <String> list) {
        return list.stream().collect(Collectors.groupingBy(w->w.charAt(0), Collectors.mapping(w->w.toUpperCase(), Collectors.toList())));
    }

    public Map <Character, List<Integer>> practice2 (List <String> list) {
        return list.stream()
                .collect(Collectors
                        .groupingBy(w->w.charAt(0),
                                Collectors.mapping(String::length, Collectors.toList())));
    }

    public Map <Boolean, Long> practice3 (List <Integer> list) {
        return list.stream().collect(Collectors.partitioningBy(a->a%2==0, Collectors.counting()));
    }

    public Map <Integer, Set<String>> practice4 (List <String> list) {
        return list.stream().collect(Collectors.groupingBy(String::length, Collectors.toSet()));
    }

    public String practice5 (List <Integer> list) {
        return list.stream().collect(Collectors.collectingAndThen(Collectors.counting(), count -> "Count " + count));
    }

    public Map <Character, String> practice6 (List <String> list) {
        return list.stream().collect(Collectors.groupingBy(w->w.charAt(0), Collectors.joining()));
    }

    public Map <Integer, Integer> practice7 (List <Integer> list) {
        return list.stream().collect(Collectors.groupingBy(a->a%3, Collectors.reducing(0,Integer::sum)));
    }

    public String practice8 (List <String> list) {
        return list.stream().reduce("", (a, b) -> a.length() >= b.length() ? a : b);
    }

    public Map <Character, List <String>> practice9 (List <String> list) {
        return list.stream().collect(Collectors
                .groupingBy(w->w.charAt(0),
                        Collectors.collectingAndThen(Collectors.toList(), words->words.stream()
                                .sorted(Comparator
                                        .comparing(String::length))
                                .toList())));
    }

    public TreeMap <Integer, List <String>> practice10 (List <String> list) {
        return list.stream().collect(Collectors
                .groupingBy(
                        String::length,
                        TreeMap::new,
                        Collectors.toList()));
    }

    public  LinkedHashMap<Integer, List<String>> practice11 ( Map<Integer, List<String>> map) {
        return map.entrySet().stream()
                .sorted(Comparator.comparingInt(e-> e.getValue().size()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a,b) ->b,
                        LinkedHashMap::new
                ));
    }

    public Map <Character, Set<String>> practice14 (List<String>list) {
        return list.stream().collect(Collectors.groupingBy(
                w->w.charAt(0),
                Collectors.toCollection(TreeSet::new)
        ));
    }





}
