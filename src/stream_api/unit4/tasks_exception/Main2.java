package stream_api.unit4.tasks_exception;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


class StringWrapper {
    private String theVal;
    public StringWrapper(String str){ this.theVal = str; }
}
public class Main2 {
            public static void main(String[] args) {
                StringWrapper sw = new StringWrapper("How are you?");
                StringBuilder sb = new StringBuilder("How are you?");
                System.out.println("Hello, "+sw);
                System.out.println("Hello, "+sb);
    }

    public static int task1(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Divide by zero");
        }
        return a / b;
    }

    public static int task2(String s) {
        if (s == null) {
            throw new IllegalArgumentException("text is null");
        }
        return s.length();
    }

    public static void task3(int i) throws Exception {
        if (i < 1 || i > 120) {
            throw new Exception("exception");
        }
    }

    public static int task4(int[] array, int index) {
        if (index >= array.length || index < 0) {
            throw new IndexOutOfBoundsException("out of array range");
        }
        return array[index];
    }

    public static int task5(String pass) throws MyInvalidPasswordException {
        if (pass.length() < 6) {
            throw new MyInvalidPasswordException("password is too short");
        }
        return pass.length();
    }


    static class MyInvalidPasswordException extends Exception {
        public MyInvalidPasswordException(String msg) {
            super(msg);
        }
    }

    public static String task6(String filePath) throws FileNotFoundException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            return bufferedReader.readLine();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static int task7(String a, String b) {
        try {

            int x = Integer.parseInt(a);
            int y = Integer.parseInt(b);
            return x / y;
        } catch (NumberFormatException | ArithmeticException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static String loadConfig (String filePath) throws IOException {
return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public static void task8 () {
        try {
            String data = loadConfig("config.text");
            System.out.println(data);
        } catch (IOException e) {
            System.out.println("load file exception" + e.getMessage());
        }
    }

    public static int task9 (String number) throws Exception {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new Exception("cannot handle exception", e);
        }
    }
}
