package stream_api.unit3.task10_;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main1 {
    public void practice1() {
        IntSummaryStatistics stats = IntStream.of(5, 10, 15, 20).summaryStatistics();
        System.out.println(stats.getAverage());
    }

    public IntSummaryStatistics practice2() {
        List<Product> products = List.of(
                new Product("Apple", 10),
                new Product("Pear", 8),
                new Product("Mango", 12)
        );

        IntSummaryStatistics stats = products.stream().collect(Collectors.summarizingInt(Product::getPrice));
        return stats;
    }

    public Map<String, Double> practice3 () {
        List<Product> items = List.of(
                new Product("fruit", 10),
                new Product("fruit", 20),
                new Product("veg", 5)
        );

        return items.stream().collect(Collectors.groupingBy(Product::getFruit, Collectors.averagingInt(Product::getPrice)));
    }

    public Map <String, IntSummaryStatistics> practice4 () {
        List<Product> items = List.of(
                new Product("fruit", 10),
                new Product("fruit", 20),
                new Product("veg", 5)
        );
        return items.stream().collect(Collectors.groupingBy(Product::getFruit, Collectors.summarizingInt(Product::getPrice)));
    }

    public Map <String, Long> practice5 () {
        List<Product> items = List.of(
                new Product("fruit", 10),
                new Product("fruit", 20),
                new Product("veg", 5)
        );
        return items.stream().collect(Collectors.groupingBy(Product::getFruit, Collectors.counting()));
    }

    public void practice6 () {
        List<String> words = List.of("apple", "ape", "banana", "ball", "berry");
        Map <Character, Double> map = words.stream().collect(Collectors.groupingBy(w->w.charAt(0), Collectors.averagingInt(String::length)));
    }

    public Map <Character, IntSummaryStatistics> practice7 () {
        List<String> words = List.of("apple", "ape", "banana", "ball", "berry");
        return words.stream().collect(Collectors.groupingBy(w->w.charAt(0), Collectors.mapping(String::length, Collectors.summarizingInt(Integer::intValue))));
    }

    public Map <Boolean, IntSummaryStatistics> practice8 () {
        List<Product> items = List.of(
                new Product("fruit", 10),
                new Product("fruit", 20),
                new Product("veg", 5)
        );
        return items.stream().collect(Collectors.partitioningBy(p->p.getPrice() >= 10, Collectors.summarizingInt(Product::getPrice)));
    }

    public void practice9 () {
        IntSummaryStatistics s1 = IntStream.of(1, 2, 3).summaryStatistics();
        IntSummaryStatistics s2 = IntStream.of(4, 5, 6).summaryStatistics();
        s1.combine(s2);
        System.out.println(s1);
    }

    public Map <String, IntSummaryStatistics> practice10 () {
        List<Product> data = List.of(
                new Product("A", 5),
                new Product("A", 15),
                new Product("B", 10),
                new Product("B", 20)
        );
        return data.stream().collect(Collectors.groupingBy(Product::getFruit, Collectors.summarizingInt(Product::getPrice)));
    }
}

class Product {
    String fruit;
    static int number;
    String category;

    Product(String fruit, int number) {
        this.fruit = fruit;
        this.number = number;
        this.category = "value";
    }

    public int getPrice() {
        return number;
    }

    public String getFruit () {
        return fruit;
    }

    public String getCategory() {return category;}

}