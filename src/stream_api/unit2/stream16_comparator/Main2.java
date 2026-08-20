package stream_api.unit2.stream16_comparator;

import java.util.Comparator;
import java.util.List;

public class Main2 {
    public static List<String> words = List.of("sky", "banana",
            "apple", "grape", "orange", "plum");
    public List<String> test1 () {
        return words.stream()
                .sorted(Comparator.comparing(Main2::getCount))
                .toList();
    }

    public static long getCount (String word) {
        return word.chars().filter(c->"aeiou".indexOf(c)>=0).count();
    }

}
