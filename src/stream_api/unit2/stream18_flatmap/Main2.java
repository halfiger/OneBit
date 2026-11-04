package stream_api.unit2.stream18_flatmap;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class Main2 {

    public List<Integer> practice0 (List <List<Integer>> list) {
        return list.stream()
                .flatMap(Collection::stream)
                .filter(a->a>50)
                .sorted(Comparator.reverseOrder())
                .toList();
    }




}
