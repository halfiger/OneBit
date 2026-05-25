package stream_api.unit2.stream20_collecting_and_then;

import java.util.*;
import java.util.stream.Collectors;

public class Main1 {
    public String task1 (String [] str)   {
        return Arrays.stream(str)
                .filter(w->w.replaceAll("[^aeiou]]", "").length() == 2)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {

                            if(list.size() != 1) {
                                throw new IllegalStateException();
                            }
                            return list.getFirst();
                        }));
    }

    public String task2 (Integer [] str) {
        return Arrays.stream(str)
                .filter(n->n%2==1)
                .map(n->n*n+"")
                .collect(Collectors.collectingAndThen(
                   Collectors.toList(), list -> String.join("; ", list)));
    }

    public int task3 (String [] s) {
        return Arrays.stream(s)
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(w->w.charAt(w.length()-1)), Map::size));
    }

    public List<String> task4 (String [] strings) {
        return Arrays.stream(strings)
                .filter(w->w.length()>4)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    public int task5 (List <Integer> list) {
        return list.stream().collect(Collectors.collectingAndThen(
                Collectors.toList(), List::size));
    }

    public String task6 (List<String>list) {
        return list.stream().sorted(Comparator.comparing(String::length).thenComparing(Comparator.reverseOrder()))
                .collect(Collectors.collectingAndThen(Collectors.toList(), List::getFirst));
    }

    public Map <Character, List<String>> task7 (List <String> list) {
        return list.stream().collect(Collectors.collectingAndThen(Collectors.groupingBy(w->w.charAt(0)), Collections::unmodifiableMap));
    }

    public String task9 (List <Integer> in) {
        return in.stream().filter(n->n%2 == 0)
                .map(String::valueOf)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> String.join(", ", list)
                ));}


}