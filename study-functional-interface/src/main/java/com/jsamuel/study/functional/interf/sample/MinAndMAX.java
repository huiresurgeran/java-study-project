package com.jsamuel.study.functional.interf.sample;

import com.jsamuel.study.functional.interf.bean.Person;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinAndMAX {

    private static final Logger logger = LoggerFactory.getLogger(MinAndMAX.class);

    public static void main(String[] args) {
        List<Person> lists = new ArrayList<Person>();
        lists.add(new Person(1L, "p1"));
        lists.add(new Person(2L, "p2"));
        lists.add(new Person(3L, "p3"));
        lists.add(new Person(4L, "p4"));

        // min&max
        min(lists);
        max(lists);
        multiple(lists);

        // summaryStatistics，需要转成IntStream
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics statistics = primes.stream().mapToInt((x) -> x).summaryStatistics();
        logger.info("max: {}", statistics.getMax());
        logger.info("min: {}", statistics.getMin());
        logger.info("sum: {}", statistics.getSum());
        logger.info("average: {}", statistics.getAverage());
        logger.info("count: {}", statistics.getCount());
    }

    public static void min(List<Person> lists) {
        Person min = lists.stream().min(Comparator.comparing(t -> t.getN())).get();
        logger.info("person min: {}", min.getN());
    }

    public static void max(List<Person> lists) {
        Person max = lists.stream().max(Comparator.comparing(t -> t.getN())).get();
        logger.info("person max: {}", max.getN());
    }

    public static void multiple(List<Person> lists) {
        Person max = lists.stream().min(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if (o1.getN() > o2.getN()) {
                    return -1;
                }
                if (o1.getN() < o2.getN()) {
                    return 1;
                }
                return 0;
            }
        }).get();
        logger.info("person multiple min: {}", max.getN());
    }
}
