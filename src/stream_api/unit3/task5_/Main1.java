package stream_api.unit3.task5_;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main1 {
    public void practice1 () {
        Stream<String> a = Stream.of("A", "B", "C");
        Stream<String> b = Stream.of("D", "E");
        Stream.concat(a,b).forEach(System.out::println);
    }

    public void practice2 () {
        List<String> one = List.of("apple", "banana", "cherry");
        List<String> two = List.of("banana", "date", "apple");
        List<String> merged = Stream.concat(one.stream(), two.stream())
                .distinct()
                .toList();
        System.out.println(merged);
    }

    public void practice3 () {
        List<List<String>> lists = List.of(
                List.of("A", "B"),
                List.of("C", "D", "E")
        );
        List <String> list = lists.stream().flatMap(Collection::stream).toList();
    }

    public void practice4 () {
        Stream<String> s1 = Stream.of("a");
        Stream<String> s2 = Stream.of("b");
        Stream<String> s3 = Stream.of("c");
        Stream <String> merged = Stream.of(s1, s2, s3).reduce(Stream::concat).orElse(Stream.empty());
        merged.forEach(System.out::println);
    }

    public void practice5 () {
        List<String> java = List.of("Spring", "Hibernate");
        List<String> js = List.of("React", "Vue");
Stream.concat(java.stream().map(j->"java" + j), js.stream().map((j->"js" + j))).forEach(System.out::println);
    }

    public void practice6 () {
        IntStream evens = IntStream.of(2, 4, 6);
        IntStream odds = IntStream.of(1, 3, 5);

        IntStream.concat(odds, evens).sorted().forEach(System.out::println);
    }

    public void practice7 () {
        Stream<String> names = Stream.of("Bob", "Ann");
        Stream<Integer> ages = Stream.of(25, 30);
        Stream.concat(names, ages.map(String::valueOf)).forEach(System.out::println);
    }

    public void practice8 () {
        Stream<String> names = Stream.of("Bob", "Ann");
        Stream<Integer> ages = Stream.of(25, 30);
        Stream.concat(names, ages.map(String::valueOf)).forEach(System.out::println);
    }

    public void practice9 () {
        try (
                Stream <String> file1 = Files.lines(Path.of("a.txt"));
                Stream <String> file2 = Files.lines(Path.of("b.txt"))) {
            Stream.concat(file1, file2).forEach(System.out::println);
            }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void practice10 () {
        Stream <String> numbers = Stream.of("1", "2");
        Stream <String> letters = Stream.of("A", "B");
        Stream <String> sighs = Stream.of("!", "?");

        String merged = Stream.of(numbers, letters, sighs)
                .reduce(Stream::concat)
                .orElse(Stream.empty())
                .collect(Collectors.joining(" "));
        System.out.println(merged);
    }


}