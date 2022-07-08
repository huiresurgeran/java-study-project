package com.jsamuel.study.functional.interf.predicate;

import java.util.function.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimplePredicate {

    private static final Logger logger = LoggerFactory.getLogger(SimplePredicate.class);

    public static void main(String[] args) {
        interf();
        lambda();
    }

    public static void interf() {
        logger.info("--------interface--------");
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                if (integer > 100) {
                    return true;
                }
                return false;
            }
        };
        logger.info("predicate.test(): {}", predicate.test(101));
        logger.info("predicate.test(): {}", predicate.test(99));
        logger.info("--------interface--------");
    }

    public static void lambda() {
        logger.info("--------lambda--------");
        // lambda表达式返回的是一个Predicate接口
        Predicate<Integer> predicate = (i) -> i > 100;
        logger.info("predicate.test(): {}", predicate.test(101));
        logger.info("predicate.test(): {}", predicate.test(99));
        logger.info("--------lambda--------");
    }
}
