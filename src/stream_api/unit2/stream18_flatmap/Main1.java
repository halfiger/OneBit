package stream_api.unit2.stream18_flatmap;

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


}
