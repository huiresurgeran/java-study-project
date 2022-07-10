package com.jsamuel.study.functional.interf.sample;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForEach {

    private static final Logger logger = LoggerFactory.getLogger(ForEach.class);

    public static void main(String[] args) {
        List features = Arrays.asList("piano", "wiolin", "guitar");
        features.forEach(n -> logger.info("feature: {}", n));

        // 方法引用，::
        features.forEach(System.out::println);
    }
}
