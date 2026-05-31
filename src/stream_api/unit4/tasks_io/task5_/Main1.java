package stream_api.unit4.tasks_io.task5_;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main1 {
    public void practice() throws IOException {
        Path source = Paths.get("data/input.csv");
        Path backupDir = Paths.get("backup");
        Files.createDirectories(backupDir);
    }


}
