package stream_api.p4;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main1 {
    public List<Integer> task1() {
        return Stream.of(10, 70, 20, 90, 55, 30)
                .filter(a -> a > 50)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    public String task2() {
        return Stream.of("Dog", "Cat", "Mister", "Thea", "Dinner", "Architector")
                .filter(w -> w.length() > 6)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.isEmpty() ? "empty" : list.get(0)));
    }

    public List<String> task3() {
        return List.of("cooperation", "stream", "banana",
                        "supernova", "moon", "queueing", "idealism")
                .stream()
                .map(String::toLowerCase)
                .sorted(Comparator.comparing(w -> w.chars().filter(c -> "aeiou".indexOf(c) >= 0).count()))
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    public List<Integer> task4() {

        return Stream.of(1, 2, 3, 4, 5)
                .filter(a -> a % 2 == 1)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    public String task5() {
        return Stream.of("cooperation", "stream", "banana",
                        "supernova", "moon", "queueing", "idealism")
                .filter(w -> w.chars().filter(c -> "aeiou".indexOf(c) >= 0).count() == 2)
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        list -> {
                            if (list.size() == 1) {
                                throw new IllegalStateException("Expected exactly one");
                            }
                            return list.get(0);
                        }));

    }
}