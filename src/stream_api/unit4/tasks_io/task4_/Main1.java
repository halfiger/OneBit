package stream_api.unit4.tasks_io.task4_;

import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.stream.Stream;

public class Main1 {
    public void practice1() throws IOException {
        Path root = Paths.get("data");
        try (var stream = Files.walk(root)) {
            stream.forEach(System.out::println);
        }
    }

    public void practice2() throws IOException {
        Path root = Paths.get("data/");
        try (var stream = Files.walk(root)) {
            long files = stream.filter(Files::isRegularFile)
                    .count();
            System.out.println("Files " + files);
        }
        try (var stream = Files.walk(root)) {
            long dirs = stream.filter(Files::isDirectory)
                    .count();
            System.out.println("Files " + dirs);
        }
    }

    public void practice3() throws IOException {
        long totalSize;
        Path root = Paths.get("data/");


        try (var stream = Files.walk(root)) {
            totalSize = stream
                    .filter(Files::isRegularFile)
                    .mapToLong(p -> {
                        try {
                            return Files.size(p);
                        } catch (Exception e) {
                            return 0L;
                        }
                    }).sum();
        }
        System.out.println(totalSize);
    }

    public void practice4() throws IOException {
        Path root = Paths.get("data/");
        try (var stream = Files.walk(root)) {
            stream.filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".txt"))
                    .forEach(System.out::println);
        }
    }

    public void practice5() throws IOException {
        Path biggest;
        Path root = Paths.get("data/");

        try (var stream = Files.walk(root)) {
            biggest = stream.filter(Files::isRegularFile)
                    .max(Comparator.comparingLong(
                            p -> {
                                try {
                                    return Files.size(p);
                                } catch (Exception e) {
                                    return 0L;
                                }
                            }
                    ))
                    .orElse(null);
        }
        System.out.println("biggest " + biggest);
    }


    public void practice6 () throws IOException {

        Path src = Paths.get("data/source.txt");
        Path dst = Paths.get("data/destination.txt");

        try {
            Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
            FileLogger.log("COPY", src + " -> " + dst, true);
        } catch (Exception e) {
            FileLogger.log("COPY", src + " -> "+  dst, false);
        }



    }
    public void practice7 () throws IOException {
        Path root = Paths.get("data/");

        try (var stream = Files.walk(root)) {
            stream.forEach(p->
                    FileLogger.log("WALK", p.toString(), true )
            );
        }
    }

    public void practice8 () throws IOException {
        long files;
        long size;
        Path root = Paths.get("data/");

        try (var stream = Files.walk(root)) {
            files = stream.filter(Files::isRegularFile)
                    .count();
        }

        try (var stream = Files.walk(root)) {
            size = stream
                    .filter(Files::isRegularFile)
                    .mapToLong(p -> {
                        try {
                            return Files.size(p);
                        } catch (Exception e) {
                            return 0L;
                        }
                    })
                    .sum();
        }
            FileLogger.log("ANALYZE", "Files=" + files + ",Size=" + size, true);
    }

    public Stream <Path> practice9 () throws IOException {
        Path root = Paths.get("data/");
        try (var stream = Files.walk(root)) {
            return stream.filter(p-> {
                try {
                    return Files.isReadable(p);
                } catch (Exception e) {
                    FileLogger.log("READ_CHECK", p.toString(), false);
                return false;
                }
            });
        }
    }






    public static class FileLogger {

        private static final Path LOG_FILE = Path.of("app.log");

        public static void log(String action, String message, boolean success) {
            String line = action + " | " + message + " | " +
                    (success ? "OK" : "FAIL") + "\n";
            try {
                Files.writeString(
                        LOG_FILE,
                        line,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND
                );
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}



