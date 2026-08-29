package stream_api.unit1.p1;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main2 {
    public List <String> practice1 (List<String> list) {
    return list.stream()
            .filter(w->w.length()>3)
            .sorted(Comparator.comparing(String::length))
            .toList();
    }

    public List <String> practice2 (List <String> list) {
        return list.stream()
                .map(String::toLowerCase)
                .sorted()
                .toList();
    }

    public String practice3 (List <String> list) {
        return list.stream()
                .map(w-> Map.entry(w,w.length()))
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("");
    }

    public Integer practice4 (Integer n) {
        return String.valueOf(n)
                .chars()
                .map(Character::getNumericValue)
                .map(d->d*d)
                .sum();
    }

    public List <Integer> practice5 (String s) {
        return s.chars()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .distinct()
                .sorted()
                .boxed()
                .toList();
    }

    public int practice6 (int n) {
        return Integer
                .parseInt(new StringBuilder(String
                        .valueOf(Math.abs(n)))
                        .reverse()
                        .toString());
    }

    public int practice7 (int n) {
        return Integer.parseInt(String.valueOf(Math.abs(n))
                .chars()
                .map(Character::getNumericValue)
                .filter(a->a%2==0)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining()));
    }

    public int practice8 (int n) {
        String s = String.valueOf(Math.abs(n));
        String result =  IntStream.range(0, s.length())
                .mapToObj(a->String.valueOf(a* Character
                        .getNumericValue(s.charAt(a))))
                .collect(Collectors.joining());
        return n < 0 ? - Integer.parseInt(result) : Integer.parseInt(result);
    }

    public long practice9 (int n) {
        return String.valueOf(Math.abs(n)).chars()
                .map(Character::getNumericValue)
                .filter(d->d>5)
                .count();
    }

    public String practice10 (int n) {
        return String.valueOf(Math.abs(n)).chars()
                .map(Character::getNumericValue)
                .mapToObj(d->d>5 ? "O" : "X")
                .collect(Collectors.joining());
    }
}
