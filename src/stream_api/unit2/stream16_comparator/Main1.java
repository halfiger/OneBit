package stream_api.unit2.stream16_comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main1 {

    public static List<String> words = List.of("sky", "banana",
            "apple", "grape", "orange", "plum");

    public List <String> task1 () {
        return words.stream()
                .sorted(Comparator.comparing(w->countVowels((String) w))
                        .reversed()).toList();
    }

    public static Long countVowels(String s) {
        return s.chars().filter(c->"aeiou".indexOf(c)>=0).count();
    }

    public Long countLetter (String s, Character c) {
        return s.chars().filter(ch->ch==c).count();
    }

    public String task2 () {
        return words.stream()
                .collect(Collectors.toMap(Function.identity(),
                        word->countLetter(word, 'a')))
                .entrySet().stream().max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("");
    }

    public int task3 (List <Integer> list) {
        return list.stream().collect(Collectors
                .toMap(Function.identity(), this::countNumbers))
                .entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(-1);
    }

    public long countNumbers (int i) {
        return String.valueOf(i).chars().distinct().count();
    }

    public String task4 (List <String> list) {
        return list.stream()
                .collect(Collectors
                        .toMap(Function.identity(), w->w.chars().sum()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

    public String task5 (String [] array) {
        return Arrays.stream(array).filter(word->word.startsWith("s"))
                .max(Comparator.comparing(String::length))
                .orElse("");
    }

    public String [] task6 (String [] array) {
        return Arrays.stream(array).sorted(Comparator.comparing(w->w.charAt(w.length()-1))).toArray(String[]::new);
    }




}
