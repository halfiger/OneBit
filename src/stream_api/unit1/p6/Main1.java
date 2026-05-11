package stream_api.unit1.p6;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main1 {
    public List<String> task2 (List <String> list) {
        return list.stream()
                .filter(w->"aeiou"
                        .chars()
                        .allMatch(c->w.indexOf(c)>=0))
                .toList();
    }

    public List <Long> task3 () {
        List<Long> top10 = new Random().ints(100, -100, 101)
                .mapToObj(String::valueOf)
                .map(Long::valueOf)
                .filter(n->n>0)
                .sorted(Comparator.reverseOrder())
                .limit(10)
                .toList();
        return top10;
    }

    public int task4 (List <String> list) {
        return list.stream().collect(Collectors.groupingBy(w->w.charAt(w.length()-1))).size();
    }

    public List<String> task5 (List <String> list) {
        return list.stream()
                .peek(System.out::println)
                .map(String::toUpperCase)
                .peek(System.out::println)
                .filter(w->w.length() > 3)
                .toList();
    }

    public int task6 (int n) {
        int [] digits = String.valueOf(Math.abs(n)).chars().map(Character::getNumericValue).toArray();
        return IntStream.range(0, digits.length).filter(i->i%2 ==0).map(i->digits[i]).reduce(1, (a,b)-> a*b);
    }

    public Map<Integer, List<String>> task7 (List <String> list) {
        return list.stream()
                .filter(w->!isPalindrom(w) || !w.isBlank())
                .collect(Collectors.groupingBy(String::length));
    }

    public boolean isPalindrom (String s) {
        return new StringBuilder(s).reverse().toString().equals(s);
    }

    public Integer task8 (List <Integer> list) {
        return list.stream()
                .max(Comparator.comparing(n->String.valueOf(n).chars().distinct().count()))
                .orElse(-1);
    }

    public Map <Character, Set<String>> task9 (List<List<String>> list) {
        return list.stream().flatMap(Collection::stream)
                .collect(Collectors.groupingBy(w->w.charAt(0), Collectors.toSet()));
    }

    public Map<Character, Set<String>> task10 (List <String> list) {
        return list.stream().filter(w->w.length() >2).map(String::toLowerCase)
                .collect(Collectors.collectingAndThen(Collectors.groupingBy(w->w.charAt(w.length()-1),
                        Collectors.mapping(
                                Function.identity(),
                                Collectors.toSet())), Collections::unmodifiableMap));
    }
}
