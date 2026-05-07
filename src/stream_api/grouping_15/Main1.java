package stream_api.grouping_15;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main1 {
    public Map<String, Boolean> task1 (List<String> list) {
        return list.stream().collect(Collectors.toMap(Function.identity(), this::hasVowels));
    }

    public boolean hasVowels (String s) {
        return s.chars().filter(c-> "aeiou".indexOf(c) >= 0).findAny().isPresent();
    }

    public long task2 (String s) {
        Map <Character, Long> map = s.toLowerCase().chars().mapToObj(c->(char)c)
                .collect(Collectors
                        .groupingBy(Function.identity(), Collectors.counting()));
        return map.entrySet().stream().filter(e->e.getValue() > 1).count();
    }


}
