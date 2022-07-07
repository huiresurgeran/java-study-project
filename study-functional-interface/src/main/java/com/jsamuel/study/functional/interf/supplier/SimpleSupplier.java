package com.jsamuel.study.functional.interf.supplier;

import java.util.function.Supplier;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleSupplier {

    private static final Logger logger = LoggerFactory.getLogger(SimpleSupplier.class);

    public static void main(String[] args) {
        interf();
        lambda();
        reference();
    }

    public static void interf() {
        logger.info("--------interface--------");
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return RandomStringUtils.randomAlphanumeric(20);
            }
        };
        logger.info("supplier.get(): {}", supplier.get());
        logger.info("--------interface--------");
    }

    public static void lambda() {
        logger.info("--------lambda--------");
        // lambda表达式返回的是一个Supplier接口
        Supplier<String> supplier = () -> RandomStringUtils.randomAlphanumeric(20);
        logger.info("supplier.get(): {}", supplier.get());
        logger.info("--------lambda--------");
    }

    public static void reference() {
        logger.info("--------reference--------");
        // 方法引用是一个supplier
        Supplier<Double> supplier = Math::random;
        logger.info("supplier.get(): {}", supplier.get());
        logger.info("--------reference--------");
    }
}
