package stream_api.unit2.stream16_comparator;

import java.util.Comparator;
import java.util.List;

public class Main1 {
    public static List<String> words = List.of("sky", "banana",
            "apple", "grape", "orange", "plum");
    public List <String> task1 () {
        return words.stream().sorted(Comparator.comparing(w->countVowels((String) w)).reversed()).toList();
    }

    public static Long countVowels(String s) {
        return s.chars().filter(c->"aeiou".indexOf(c)>=0).count();
    }

}
