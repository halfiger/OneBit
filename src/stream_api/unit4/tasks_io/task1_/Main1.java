package stream_api.unit4.tasks_io.task1_;

import java.io.*;

public class Main1 {
    public void task1() {
        File file = new File("data/example/text");
        try {
            if (file.createNewFile()) {
                System.out.println("File");
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("Error creating file");
        }
    }

    public void task2() {
        File file = new File("data/example.txt");
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
            System.out.println("directory created");
        } else {
            System.out.println("directory already exists");
        }
    }

    public void task3() {
        try (FileWriter writer = new FileWriter("data/example.txt")) {
            writer.write("hello java io");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void task4() {
        try (FileWriter writer = new FileWriter("data/example.txt", true)) {
            writer.write(" + hello java io");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void task5() throws IOException {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader("data/example.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void task6() throws IOException {
        try (FileWriter writer = new FileWriter("data/example.txt")) {

            for (int i = 0; i <= 5; i++) {
                writer.write(String.valueOf(i));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void task7() throws FileNotFoundException {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader("data/numbers.txt"))) {
            int sum = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                sum += Integer.parseInt(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
