package stream_api.unit4.tasks_io.task3_;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main1 {

    public void practice1() throws IOException {
        Path source = Paths.get("data/source.txt");
        Path target = Paths.get("data/copy.txt");
        Files.copy(source, target);
    }

    public void practice2() throws IOException {
        Path source = Paths.get("data/source.txt");
        Path target = Paths.get("data/target.txt");


        Files.copy(
                source,
                target,
                StandardCopyOption.REPLACE_EXISTING
        );
    }

    public void practice3() throws IOException {
        Path source = Paths.get("data/source.txt");
        Path target = Paths.get("data/target.txt");
        if (!Files.exists(source)) {
            System.out.println("source not found");
            return;
        }

        Files.copy(source,
                target,
                StandardCopyOption.REPLACE_EXISTING);
    }

    public void practice4() throws IOException {
        Path source = Paths.get("data/source.txt");
        Path target = Paths.get("data/target.txt");
        Files.createDirectories(target.getParent());
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
    }

    public void practice5() throws IOException {
        Path target = Paths.get("archive/source.txt");
        Path source = Paths.get("data/source.txt");

        Files.createDirectories(target.getParent());
        Files.createDirectories(target.getParent());

        Files.move(source, target,
                StandardCopyOption.REPLACE_EXISTING
        );
    }

    public void practice6() throws IOException {
        Path oldName = Paths.get("data/report.txt");
        Path newName = Paths.get("data/report_old.txt");
        Files.move(oldName, newName, StandardCopyOption.REPLACE_EXISTING);
    }

    public void practice7() throws IOException {
        Path source = Paths.get("data/config.txt");
        Path backup = source.resolveSibling(
                source.getFileName() + ".bak"
        );

        Files.copy(source,
                backup,
                StandardCopyOption.REPLACE_EXISTING);
    }

    public void practice8() throws IOException {
        Path source = Paths.get("data/source.txt");
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path backup = source.resolveSibling(
                source.getFileName() + "_" + timestamp + ".bak"
        );
        Files.copy(source, backup);
    }

    public void practice9() throws IOException {
        Path sourceDir = Paths.get("data");
        Path backupDir = Paths.get("backup");

        Files.createDirectories(backupDir);
        try (var stream = Files.list(sourceDir)) {
            stream.filter(Files::isRegularFile)
                    .forEach(path -> {
                        try {
                            Files.copy(path,
                                    backupDir.resolve(path.getFileName()),
                                    StandardCopyOption.REPLACE_EXISTING
                            );
                        } catch (Exception e) {
                            System.out.println("Exception " + path);
                        }
                    });
        }
    }

    public void practice10 () throws IOException {
        Path tmp = Paths.get("backup/config.tmp");
        Path source = Paths.get("source/config.bak");
        Path backup = Paths.get("backup/config.bak");

        Files.copy(source, tmp, StandardCopyOption.REPLACE_EXISTING);
        Files.move(
                tmp,
                backup,
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.ATOMIC_MOVE
        );


    }

}
