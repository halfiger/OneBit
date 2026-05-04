package stream_api.p5;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main1 {

    public static long countVowels(String s) {
        return s.chars()
                .filter(c -> "aeiou".indexOf(c) >= 0)
                .count();
    }


    public List<String> task1() {

        return Stream.of("cooperation", "stream", "banana",
                        "supernova", "moon", "queueing", "idealism")
                .sorted(Comparator.comparing(Main1::countVowels).thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList());
    }

    public List<Integer> task2(List<Integer> numbers) {
        return numbers.stream().sorted(Comparator.comparing((Integer a) -> a % 2 == 0)
                        .thenComparing(Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public LinkedHashMap <Integer, List <String>> task3 (Map<Integer,List<String>> map) {
        return map.entrySet()
                .stream()
                .sorted(Comparator.comparing(e->e.getValue().size()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a,b) -> b,
                        LinkedHashMap::new));
    }

    public List <String> task4 (List <String> words) {
        return words
                .stream()
                .sorted(Comparator.comparing(String::length).reversed())
                .toList();
    }

    public LinkedHashMap <Character, Integer> task5 (Map <Character, Integer> map) {
        return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a,b) -> b,
                        LinkedHashMap::new
                ));
    }

    public String task6 (List <String> list) {

        return list.stream()
                .max(Comparator.comparing(w->w.chars().filter(c->c=='a').count()))
                .orElse("");
    }

    public List <String> task7 (List <String> words) {
        return words
                .stream()
                .sorted(Comparator.comparing(Main1::countVowels).thenComparing(String::length))
                .toList();
    }

    public List <String> task8 (Map <String, Long> map, int n) {
        return map.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(n)
                .map(Map.Entry::getKey)
                .toList();
    }

    public Map <Long, Long> task9 (List<Long> list) {
        return list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Character task10 (String [] array) {
        return Arrays.stream(array).collect(Collectors.groupingBy(w->w.charAt(0), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(' ');
    }

}
