package com.jsamuel.study.functional.interf.supplier;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OptionalSupplier {

    private static final Logger logger = LoggerFactory.getLogger(OptionalSupplier.class);

    public static void main(String[] args) {
        optional();
    }

    public static void optional() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> filterStream = stream.filter(i -> i > 3);
        Optional<Integer> first = filterStream.findFirst();

        // orElse: first中存在，返回；不存在，返回括号中传入数据
        logger.info("first: {}", first.orElse(1));
        logger.info("first: {}", first.orElse(5));

        // supplier
        Supplier<Integer> supplier = () -> new Random().nextInt();

        // orElseGet: first中存在，返回；不存在，返回supplier返回的值
        logger.info("first: {}", first.orElseGet(supplier));

    }
}
