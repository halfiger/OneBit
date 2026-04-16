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
    }

    public void task1 (int i) {
        switch (i) {
            case 1 :
                System.out.println("Monday");
                break;
            case 2 :
                System.out.println("Tuesday");
                break;
            case 3 :
                System.out.println("Wednesday");
                break;
            default :
                System.out.println("other");
                break;
        }
    }

    public void task2 (char c) {
        switch (c) {
            case 'A' :
                System.out.println("Excelent");
                break;
            case 'B' :
                System.out.println("Good");
                break;
            case 'C' :
                System.out.println("Average");
                break;
            default :
                System.out.println("Fail");
        }




    }




}