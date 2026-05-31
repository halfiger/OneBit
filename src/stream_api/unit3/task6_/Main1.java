package stream_api.unit3.task6_;

import java.util.*;
import java.util.stream.Collectors;

public class Main1 {
    public void practice1 () {
        List<String> names = List.of("Bob", "Anna", "John");

        Map<String, Integer> map = names.stream()
                .collect(Collectors.toMap(
                        name-> name,
                        String::length
                ));
        System.out.println(map);
    }

    public void practice2 () {
        List <String> names = List.of("Bob", "Bill", "Anna");
        Map<Character, String> map = names.stream()
                .collect(Collectors.toMap(
                        name -> name.charAt(0),
                        name->name
                ));
        System.out.println(map);
    }

    public void practice3 () {
        List <String> names = List.of("Bob", "Bill", "Anna");

        Map <Character, String> map = names.stream()
                .collect(Collectors.toMap(
                        name->name.charAt(0),
                        name -> name,
                        (v1, v2) -> v1 + ", " + v2
                ));
        System.out.println(map);
    }

    public void practice4 () {
        List<String> names = List.of("Bob", "Bill", "Benjamin");
        Map <Character, String> map = names.stream().collect(Collectors.toMap(
                s->s.charAt(0),
                s->s,
                (a,b) -> a.length() <= b.length() ? a:b
        ));
    }

    public void practice5 () {
        List<String> fruits = List.of("apple", "pear", "apple", "banana", "pear");
        Map <String, Long> map = fruits.stream()
                .collect(Collectors.toMap(
                        f->f,
                        f->1L,
                        Long::sum
                ));
        System.out.println(map);
    }

    public void practice6 () {
        List<String> words = List.of("cat", "cow", "dog", "duck");
        Map <Character, List <String>> map = words.stream()
                .collect(Collectors.toMap(
                        w->w.charAt(0),
                        w-> new ArrayList<>(List.of(w)),
                        (list1, list2) -> {list1.addAll(list2); return list1;}
                ));
        System.out.println(map);
    }

    public void practice7 () {
        List<Map<String, Integer>> maps = List.of(
                Map.of("A", 1, "B", 2),
                Map.of("B", 3, "C", 4)
        );

        Map <String, Integer> merged = maps.stream()
                .flatMap(m->m.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Integer::sum
                ));
        System.out.println(merged);
    }

    public void practice8 () {
        List<String> items = List.of("apple", "apple", "pear", "apple", "pear");
        Map <String, Integer> result = items.stream()
                .collect(Collectors.toMap(
                        s->s,
                        s->1,
                        Integer::sum
                ));
        System.out.println(result);
    }

    public void practice9 () {
        List<String> words = List.of("java", "jungle", "joy");
        Map <Character, String> result = words.stream()
                .collect(Collectors.toMap(
                        w-> w.charAt(0),
                        w->w + "(" + w.length() + ")",
                        (a,b) -> a + "; " + b
                ));

        System.out.println(result);
    }

    public void practice10 () {
        List <String> words = List.of("apple", "apricot", "banana", "blueberry", "apple");
        Map <Character, Set<String>> map = words.stream()
                .collect(Collectors.toMap(
                        w->w.charAt(0),
                        w-> new HashSet<>(Set.of(w)),
                        (set1, set2) -> {
                            set1.addAll(set2); return set1;
                        }
                ));
        System.out.println(map);
    }
}
