package com.jsamuel.study.functional.interf.sample;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Distinct {

    private static final Logger logger = LoggerFactory.getLogger(Distinct.class);

    public static void main(String[] args) {
        // 去重
        List<String> list = Arrays.asList("toto", "sherry", "toto", "yuki");
        List<String> result = list.stream().distinct().collect(Collectors.toList());
        result.forEach(str -> logger.info("name: {}", str));
    }
}
