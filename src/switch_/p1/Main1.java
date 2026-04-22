package switch_.p1;

public class Main1 {
    public static void main(String[] args) {
        Main1 main = new Main1();
        main.task1(1);
        main.task1(2);
        main.task1(3);
        main.task1(4);

        System.out.println("= = = = = = = = = = = =");

        main.task2('A');
        main.task2('B');
        main.task2('C');
        main.task2('D');

        System.out.println("= = = = = = = = = = = =");

        main.task3("USER");
        main.task3("ADMIN");
        main.task3("NEW");

        System.out.println("= = = = = = = = = = = =");

        main.task4(1);
        main.task4(2);
        main.task4(3);
        main.task4(4);
        main.task4(5);
        main.task4(6);
        main.task4(7);
        main.task4(8);

        System.out.println("= = = = = = = = = = = =");

        main.task5(1);
        main.task5(2);
        main.task5(3);
        main.task5(4);
        main.task5(5);
        main.task5(6);
        main.task5(7);

        System.out.println("= = = = = = = = = = = =");
    }

    public void task1(int i) {
        switch (i) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            default:
                System.out.println("other");
                break;
        }
    }

    public void task2(char c) {
        switch (c) {
            case 'A':
                System.out.println("Excelent");
                break;
            case 'B':
                System.out.println("Good");
                break;
            case 'C':
                System.out.println("Average");
                break;
            default:
                System.out.println("Fail");
        }
    }

    public void task3(String s) {
        switch (s) {
            case "ADMIN":
                System.out.println("ADMINISTRATOR");
                break;
            case "USER":
                System.out.println("USER");
                break;
            default:
                System.out.println("GUEST");
        }
    }

    public void task4(int i) {
        switch (i) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
            default:
                System.out.println("other");
        }
    }

    public void task5(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
                System.out.println("low");
                break;
            case 4:
            case 5:
                System.out.println("Medium");
                break;
            case 6:
                System.out.println("High");
                break;
            default:
                System.out.println("other");
        }
    }

    public void task6() {
        int month = 0;
        switch (month) {
            case 1:
            case 2:
            case 3:
                System.out.println("Winter");
                break;
            default:
                System.out.println("Not winter");
        }
    }

    public void task7(int number) {
        switch (number % 2) {
            case 0:
                System.out.println("odd");
                break;
            case 1:
                System.out.println("even");
                break;
            default:
                System.out.println("fault");
        }
    }

    public void task8(String sentense) {
        switch (sentense) {
            case "USD":
                System.out.println("dolyar");
                break;
            case "EUR":
                System.out.println("euro");
                break;
            case "UAH":
                System.out.println("grivna");
                break;
            default:
                System.out.println("other");
        }
    }

    public void task9(char c) {
        int a = 3;
        int b = 5;

        switch (c) {
            case '+':
                System.out.println(a + b);
                break;
            case '-':
                System.out.println(a - b);
                break;
            case '*':
                System.out.println(a * b);
                break;
        }
    }

    public void task10(char c) {
        switch (c) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                System.out.println("Vowel");
                break;
            default:
                System.out.println("Consosnant");
        }
    }

    public String task11(int day) {
        return switch (day) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            default -> "Unknown";
        };
    }

    public String task12(int number) {
        return switch (number) {
            case 1, 2, 3 -> "Low";
            case 4, 5 -> "Medium";
            default -> "High";
        };
    }

    public String task13(String role) {
        return switch (role) {
            case "ADMIN" -> "Full";
            case "USER" -> "Limited";
            default -> "GUEST";
        };
    }

    public int task14(char c, int a, int b) {

        return switch (c) {
            case '+' -> a + b;
            case '-' -> a - b;
            default -> '0';
        };
    }


}