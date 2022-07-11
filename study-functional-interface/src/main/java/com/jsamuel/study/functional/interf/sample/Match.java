package com.jsamuel.study.functional.interf.sample;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Match {

    private static final Logger logger = LoggerFactory.getLogger(Match.class);

    public static void main(String[] args) {
        List<String> list = Arrays.asList("asd", "bnm", "qwe");

        // 匹配任何一个
        boolean anyStartsWithA = list.stream().anyMatch(s -> s.startsWith("a"));
        logger.info("anyStartsWithA: {}", anyStartsWithA);

        // 全部匹配
        boolean allStartsWithA = list.stream().allMatch(s -> s.startsWith("a"));
        logger.info("allStartsWithA: {}", allStartsWithA);

        // 不匹配
        boolean noneStartsWithA = list.stream().noneMatch(s -> s.startsWith("z"));
        logger.info("noneStartsWithA: {}", noneStartsWithA);


    }
}
