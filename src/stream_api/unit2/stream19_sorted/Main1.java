package stream_api.unit2.stream19_sorted;

import java.util.Comparator;
import java.util.List;

public class Main1 {

    public String [] task1 (List<String> list) {
        return list.stream()
                .filter(w->w.length()>3)
                .sorted(Comparator.comparing(String::length)
                        .reversed())
                .toArray(String[]::new);
    }

    public String [] task2 (List <String> list) {
        return list.stream().map(String::toLowerCase)
                .sorted()
                .toArray(String[]::new);
    }

    public Integer [] task3 (List <Integer> list) {
        return list.stream().sorted(Comparator.reverseOrder())
                .toArray(Integer[]::new);
    }

    public Integer [] task5 (List <Integer> list) {
        return list.stream().sorted(Comparator.comparing(n->(int)n%2).thenComparing(n->(int)n))
                .toArray(Integer[]::new);
    }

    public String [] task6 (List <String> list) {
        return list.stream().sorted(Comparator.comparing(w->w.charAt(w.length()-1))).toArray(String[]::new);
    }

    public String [] task8 (List <String> list) {
        return  list.stream().sorted(Comparator.comparing(String::length).reversed()).toArray(String[]::new);
    }

    public Integer [] task9 (List <Integer> list) {
        return list.stream().sorted(Comparator.comparing(a->(int)a%2==0).thenComparing(n->(int)n)).toArray(Integer[]::new);
    }

    public Person [] task10 () {
        List<Person> people = List.of(
                new Person("Anna", 30),
                new Person("Bob", 25),
                new Person("Charlie", 22),
                new Person("Eve", 28)
        );

        return people.stream()
                .sorted(Comparator.comparing((Person p)->p.name.length())
                        .thenComparing((Person p)->p.age))
                .toArray(Person []::new);

    }

}

class Person {
    String name;
    int age;
    Person (String name, int age) {
        this.name = name;
        this.age = age;
    }
}
