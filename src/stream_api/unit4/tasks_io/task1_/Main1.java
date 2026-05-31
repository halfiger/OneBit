package stream_api.unit4.tasks_io.task1_;

import java.io.File;
import java.io.IOException;

public class Main1 {
    public void practice1() {
        File file = new File("data/example/txt");
        try {
            if (file.createNewFile()) {
                System.out.println("File created");
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("Eroor creating file, folder doesn't exists " + e.getMessage());
        }
    }


}
