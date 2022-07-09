package com.jsamuel.study.functional.interf.stream;

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
}
