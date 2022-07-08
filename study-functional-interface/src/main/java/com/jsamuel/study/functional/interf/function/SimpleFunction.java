package com.jsamuel.study.functional.interf.function;

import java.util.function.Function;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleFunction {

    private static final Logger logger = LoggerFactory.getLogger(SimpleFunction.class);

    public static void main(String[] args) {
        interf();
    }

    public static void interf() {
        logger.info("--------interface--------");
        // 第一个参数是转换前的类型，第二个参数是转换后的类型
        Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String str) {
                return str.length();
            }
        };
        Stream<String> stringStream = Stream.of("aaa", "bcbbddfds", "s");
        Stream<Integer> integerStream = stringStream.map(function);
        integerStream.forEach(System.out::println);
        logger.info("--------interface--------");
    }

}
