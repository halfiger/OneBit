package stream_api.p1;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main1 {
    List<String> list1 = List.of("A", "B", "C");
    List<String> list2 = List.of("Agava", "Bob", "Cola");


    public List<String> task1 () {
        return list1.stream()
                .filter(w->w.length() > 3)
                .sorted(Comparator.comparing(String::length))
                .toList();
    }

    public List<String> task2 () {
        return list1.stream().map(String::toLowerCase)
                .sorted()
                .toList();
    }

    public String task3 () {
        return list2.stream().map(w-> Map.entry(w, w.length()))
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("");
    }

    public int task4 (int n) {
        return String.valueOf(Math.abs(n))
                .chars()
                .map(Character::getNumericValue)
                .map(d->d*d)
                .sum();
    }

    public List<Integer> task5 (String s) {
        return s.chars()
                .filter(Character::isDigit)
                .mapToObj(Character::getNumericValue)
                .distinct()
                .sorted()
                .toList();
    }

    public int task6 (int n) {
        return Integer
                .parseInt(new StringBuilder(String
                        .valueOf(Math.abs(n)))
                        .reverse()
                        .toString());
    }

    public int task7 (int n) {
        return Integer.parseInt(String.valueOf(n).chars()
                .map(Character::getNumericValue)
                .filter(a->a%2 == 0)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining()));
    }

    public int task8 (int n) {
        String s = String.valueOf(Math.abs(n));
        String result = IntStream.range(0, s.length())
                .mapToObj(i->String.valueOf((s.charAt(i) - '0') * (i+1)))
                .collect(Collectors.joining());
        return n<0 ? -Integer.parseInt(result) : Integer.parseInt(result);
    }

    public long task9 (int n) {
        return String.valueOf(Math.abs(n))
                .chars()
                .map(Character::getNumericValue)
                .filter(d->d>5)
                .count();
    }

    public String task10 (int n) {
        return String.valueOf(Math.abs(n)).chars()
                .map(c->c-'0')
                .mapToObj(d->d>5 ? "X" : "O")
                .collect(Collectors.joining());
    }
}