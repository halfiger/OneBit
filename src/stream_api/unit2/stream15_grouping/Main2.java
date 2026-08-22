package stream_api.unit2.stream15_grouping;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main2 {

    public Map<String, Boolean> task1 (String [] words) {
        return Arrays.stream(words)
                .collect(Collectors.toMap(Function.identity(), Main2::check));
    }

    public static boolean check(String s) {
        return s.chars().anyMatch(c->"aeiuo".indexOf(c)>=0);
    }

    public Map <Character, Long> task2 (String s) {
        return s.toLowerCase()
                .chars()
                .mapToObj(c->(char)c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }


}
