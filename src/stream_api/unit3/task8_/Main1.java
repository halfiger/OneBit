package stream_api.unit3.task8_;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main1 {

    public void practice1() {
        Optional<String> name = Optional.of("JAVA");
        name.stream().forEach(System.out::println);
    }

    public void practice2() {
        Optional<String> empty = Optional.empty();
        empty.stream().forEach(System.out::println); // empty result, nothing
    }

    public void practice3() {
        Optional<List<Integer>> maybeList = Optional.of(List.of(1, 2, 3));
        maybeList.stream().flatMap(List::stream).forEach(System.out::println);
    }

    public void practice4() {
        List<String> words = List.of("cat", "apple", "pear");
        Optional<String> found = words.stream().filter(w -> w.length() > 4).findFirst();
        found.stream().forEach(System.out::println);
    }

    public void practice5() {
        Stream<Optional<String>> optionals = Stream.of(
                Optional.of("A"),
                Optional.empty(),
                Optional.of("A")
        );
        List<String> list = optionals.flatMap(Optional::stream).toList();
        System.out.println(list);
    }

    public void practice6() {
        Optional<Optional<String>> nested = Optional.of(Optional.of("Hello"));
        nested.stream()
                .flatMap(Optional::stream)
                .forEach(System.out::println);
    }

    public void practice7() {
        List<String> users = List.of("Bob", "Alexander", "Tom");
        String result = users.stream()
                .filter(u -> u.length() > 5)
                .findFirst()
                .orElse("none");
        System.out.println(result);
    }

    public void practice9() {
        Optional<String> word = Optional.of("Stream");
        int len = word.stream().map(String::length).findFirst().orElse(0);
    }

    public void practice10() {
        List<Optional<String>> optionals = List.of(
                Optional.of("red"),
                Optional.empty(),
                Optional.of("green"),
                Optional.of("blue")
        );
        String result = optionals.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.joining(", "));
        System.out.println(result);
    }
}
