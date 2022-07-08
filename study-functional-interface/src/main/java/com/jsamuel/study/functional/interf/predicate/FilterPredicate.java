package com.jsamuel.study.functional.interf.predicate;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilterPredicate {

    private static final Logger logger = LoggerFactory.getLogger(FilterPredicate.class);

    public static void main(String[] args) {
        interf();
    }

    public static void interf() {
        // predicate当做filter接口，判断
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                if (integer > 100) {
                    return true;
                }
                return false;
            }
        };

        Stream<Integer> stream = Stream.of(1, 234, 34, 67, 457, 48);
        List<Integer> list = stream.filter(predicate).collect(Collectors.toList());
        list.forEach(System.out::println);
    }
}
