package stream_api.unit4.tasks_io.task2_;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main1 {
    public void practice1 () {
        Path path = Paths.get("data/nio/example.txt");
        System.out.println(path);
    }

    public void practice2 () {
        Path path = Paths.get("data/nio/example.txt");
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void practice3 () {
        Path path = Paths.get("data/nio/example.txt");

        try {
        if (Files.notExists(path)) {
            Files.createFile(path);
        }}
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void practice4 () throws IOException {
        Path path = Paths.get("data/nio/example.txt");
        Files.write(path, List.of("Hello NIO"));

    }

    public void practice5 () throws IOException {
        Path path = Paths.get("data/nio/example.txt");
        Files.write(path, List.of("Second line"),
                StandardOpenOption.APPEND
        );
    }

    public void practice6 () throws IOException {
        Path path = Paths.get("data/nio/example.txt");
        List <String> lines = Files.readAllLines(path);
        System.out.println(lines);
    }

    public void practice7 () throws IOException {
        Path path = Paths.get("data/nio/example.txt");
        try (var stream1 = Files.lines(path)) {
            stream1.forEach(System.out::println);
        }
    }

    public void practice8 () throws IOException {
        Path path = Paths.get("data/nio/example.txt");
        int sum;
        try (var stream = Files.lines(path)) {
            sum = stream.map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .sum();
        }
        System.out.println(sum);
    }

    public void practice9 () {
        Path path = Paths.get("data/nio/example.txt");
        try (var stream = Files.lines(path)) {
            int sum = stream.map(String::trim)
                    .filter(s->s.matches("-?\\d+"))
                    .mapToInt(Integer::parseInt)
                    .sum();
            System.out.println(sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void practice10 () throws IOException {
        Path path = Paths.get("data/nio/example.txt");
        System.out.println("File deleted is " + Files.deleteIfExists(path));
    }





}
