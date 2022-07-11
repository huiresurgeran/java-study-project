package com.jsamuel.study.functional.interf.sample;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Count {

    private static final Logger logger = LoggerFactory.getLogger(Count.class);

    public static void main(String[] args) {
        // 计算总数
        long count = Arrays.asList(1, 2, 3, 4, 5).stream()
                .filter(p -> p > 3)
                .count();
        logger.info("count: {}", count);
    }
}
