package com.jsamuel.study.functional.interf.stream;

import com.jsamuel.study.functional.interf.bean.Person;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleStream {

    private static final Logger logger = LoggerFactory.getLogger(SimpleStream.class);

    public static void main(String[] args) {
        performance();
    }

    public static void performance() {
        long t0 = System.nanoTime();
        int a[] = IntStream.range(0, 1_000_000).filter(p -> p % 2 == 0).toArray();
        long t1 = System.nanoTime();
        int b[] = IntStream.range(0, 1_000_000).parallel().filter(p -> p % 2 == 0).toArray();
        long t2 = System.nanoTime();

        logger.info("serial time: {}", t1 - t0);
        logger.info("parallel time: {}", t2 - t1);
    }

    public static void min() {
        List<Person> list1 = new ArrayList<Person>();
        list1.add(new Person(1L, "p1"));
        list1.add(new Person(2L, "p2"));
        list1.add(new Person(3L, "p3"));
        list1.add(new Person(4L, "p4"));
        logger.info("list1: {}", list1);

        Person a = list1.stream().min(Comparator.comparing(t -> t.getN())).get();
        logger.info("success get min: {}", a.getN());
    }

    public static void peek() {
        List<Person> list1 = new ArrayList<Person>();
        list1.add(new Person(1L, "p1"));
        list1.add(new Person(2L, "p2"));
        list1.add(new Person(3L, "p3"));
        list1.add(new Person(4L, "p4"));
        logger.info("list1: {}", list1);

        List<Person> list2 = list1.stream()
                .filter(f -> f.getS().startsWith("p"))
                .peek(t -> {
                    logger.info("str: {}", t);
                })
                .collect(Collectors.toList());

        logger.info("list2: {}", list2);

    }

}
