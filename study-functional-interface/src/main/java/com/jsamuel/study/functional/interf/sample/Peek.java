package com.jsamuel.study.functional.interf.sample;

import com.jsamuel.study.functional.interf.bean.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Peek {

    private static final Logger logger = LoggerFactory.getLogger(Peek.class);

    public static void main(String[] args) {
        List<Person> list1 = new ArrayList<Person>();
        list1.add(new Person(1L, "p1"));
        list1.add(new Person(2L, "p2"));
        list1.add(new Person(3L, "p3"));
        list1.add(new Person(4L, "t4"));
        list1.forEach(p -> logger.info("number: {}, str: {}", p.getN(), p.getS()));

        List<Person> list2 = list1.stream()
                .filter(f -> f.getS().startsWith("p"))
                .peek(t -> {
                    logger.info("str: {}", t.getS());
                })
                .collect(Collectors.toList());
        list2.forEach(p -> logger.info("number: {}, str: {}", p.getN(), p.getS()));
    }
}
