package switch_.p1;

public class Main1 {
    public static void main(String[] args) {
        Main1 main = new Main1();
        main.task1(1);
        main.task1(2);
        main.task1(3);
        main.task1(4);


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
}