package com.jsamuel.study.functional.interf.sample;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilterAndPredicate {

    private static final Logger logger = LoggerFactory.getLogger(FilterAndPredicate.class);

    public static void main(String[] args) {
        List<String> languages = Arrays.asList("Java", "C++", "GO", "Javascript");

        logger.info("Language starts with J: ");
        languages.stream()
                .filter((str) -> str.startsWith("J"))
                .forEach((name) -> logger.info("name: {}", name));

        logger.info("Language end with a: ");
        languages.stream()
                .filter((str) -> str.endsWith("a"))
                .forEach((name) -> logger.info("name: {}", name));

        logger.info("print all languages: ");
        languages.stream()
                .filter((str) -> true)
                .forEach((name) -> logger.info("name: {}", name));

        logger.info("print no language: ");
        languages.stream()
                .filter((str) -> false)
                .forEach((name) -> logger.info("name: {}", name));

        logger.info("print language whose length greater than 4: ");
        languages.stream()
                .filter((str) -> str.length() > 4)
                .forEach((name) -> logger.info("name: {}", name));

        logger.info("print language starts with J and length is 4: ");
        multiple(languages);

    }

    public static void filter(List names, Predicate condition) {
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> logger.info("name: {}", name));
    }

    public static void multiple(List names) {
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        // 多个Predicate组合，可以用and，or，xor
        // pre1.and(pre2)
        names.stream()
                .filter(startsWithJ.and(fourLetterLong))
                .forEach((name) -> logger.info("name: {}", name));
    }
}
