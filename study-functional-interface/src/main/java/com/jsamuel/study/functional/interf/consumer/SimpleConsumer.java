package com.jsamuel.study.functional.interf.consumer;

import java.util.function.Consumer;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleConsumer {

    private static final Logger logger = LoggerFactory.getLogger(SimpleConsumer.class);

    public static void main(String[] args) {
        interf();
        lambda();
        reference();
    }

    public static void interf() {
        logger.info("--------interface--------");
        Stream<String> stringStream = Stream.of("a", "b", "c", "d", "e");
        // 实现consumer接口
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                logger.info("str: {}", s);
            }
        };
        // forEach需要Consumer类型的参数
        stringStream.forEach(consumer);
        logger.info("--------interface--------");
    }

    public static void lambda() {
        logger.info("--------lambda--------");
        Stream<String> stringStream = Stream.of("a", "b", "c", "d", "e");
        // lambda表达式返回的是一个Consumer接口
        Consumer<String> consumer = s -> logger.info("str: {}", s);
        // forEach需要Consumer类型的参数
        stringStream.forEach(consumer);
        logger.info("--------lambda--------");
    }

    public static void reference() {
        logger.info("--------reference--------");
        Stream<String> stringStream = Stream.of("a", "b", "c", "d", "e");
        // 方法引用是一个consumer
        Consumer<String> consumer = logger::info;
        // forEach需要Consumer类型的参数
        stringStream.forEach(consumer);
        logger.info("--------reference--------");
    }

}
