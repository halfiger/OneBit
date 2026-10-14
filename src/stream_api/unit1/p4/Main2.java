package stream_api.unit1.p4;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main2 {

    public List <Integer> practice1 () {
        return Stream.of(10,70,20,90,55,30)
                .filter(a->a>50)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    public String practice3 () {
        return Stream.of("Do0000g", "Ca0000t", "Mister", "Thea")
                .filter(w-> w.length() > 6)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list-> list.isEmpty() ? "empty" : list.getFirst()));
    }

    public String practice4 () {
        return Stream.of(1,2,3,4,5)
                .filter(a->a%2==1)
                .map(n->n+"")
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> String.join("", list)));
    }

}
