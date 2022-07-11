package com.jsamuel.study.functional.interf.sample;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapAndReduce {

    private static final Logger logger = LoggerFactory.getLogger(MapAndReduce.class);

    public static void main(String[] args) {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);

        double bill = costBeforeTax.stream()
                // 集合类元素转换
                .map((cost) -> cost + .12 * cost)
                // 所有值合并成一个
                .reduce((sum, cost) -> sum + cost)
                .get();
        logger.info("total expense: {}", bill);
    }
}
