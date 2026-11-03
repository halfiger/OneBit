package stream_api.unit2.stream18_flatmap;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Main1 {

    public List <Integer> practice0 () {
        List<List<Integer>> weeklyData = List.of(
                List.of(40, 55, 60),
                List.of(30, 70),
                List.of(65, 10, 90)
        );

        return weeklyData.stream()
                .flatMap(Collection::stream)
                .toList();

    }

    public List <String> practice1 (List <List <String>> list) {
        return list.stream().flatMap(Collection::stream).toList();
    }

    public List <Integer> practice2 (List <List<Integer>> list) {
        return list.stream().flatMap(Collection::stream).toList();
    }

    public Integer practice3 () {

        int[][] data = {
                {-3, 5},
                {2},
                {-1, 4}
        };

        return Arrays.stream(data).flatMap(arr -> Arrays.stream(arr).boxed())
                .filter(a->a>0)
                .reduce((a,b)->a*b)
                .orElse(-1);
    }




}
