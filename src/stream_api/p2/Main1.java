package stream_api.p2;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main1 {
    public List <String> task1 (List<String> sentences) {
        return sentences.stream()
                .flatMap(s-> Arrays.stream(s.split("\\s+")))
                .map(String::toLowerCase)
                .distinct()
                .toList();
    }

    public int task2 (int [][] array) {
        return Arrays
                .stream(array)
                .flatMapToInt(Arrays::stream)
                .filter(a->a%2 == 0)
                .min()
                .orElse(-1);
    }

    public List<Integer> task3 (List<List<Integer>> list) {
        return list.stream().flatMap(Collection::stream)
                .filter(i->i>0)
                .sorted()
                .toList();
    }

    public List <String> task4 (List<String> emails) {
        return emails.stream()
                .map(str -> str.substring(0, str.indexOf('@')))
                .sorted(Comparator.comparing(String::length))
                .toList();
    }

    public List <String> task5 (String s) {
        return s.chars()
                .distinct()
                .mapToObj(c->String.valueOf((char)c))
                .toList();
    }

    public List<String> task6 (String s) {
        return s.chars()
                .filter(Character::isUpperCase)
                .mapToObj(c->String.valueOf((char)c))
                .toList();
    }

    public List<Integer> task7 (String s) {
        return s.chars()
                .map(c->c-'a'+1)
                .boxed()
                .toList();
    }

    public List<Character> task8 (String s) {
        return s.chars().filter(Character::isAlphabetic)
                .mapToObj(c->(char)c)
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    public String task9 (List<String> list) {
        return list
                .stream()
                .max(Comparator.comparingInt(w->w.chars().sum()))
                .orElse("");
    }

    public Map<Character, Long> task10 (String s) {
        return s.chars().mapToObj(c->(char)c)
                .collect(Collectors
                        .groupingBy(Function.identity(),
                                Collectors.counting()));
    }
}
