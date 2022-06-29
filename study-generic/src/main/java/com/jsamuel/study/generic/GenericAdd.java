package com.jsamuel.study.generic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericAdd {

    private static final Logger logger = LoggerFactory.getLogger(GenericAdd.class);

    public static <T extends Number> double add(T a, T b) {
        logger.info("{} + {} = {}", a, b, (a.doubleValue() + b.doubleValue()));
        return a.doubleValue() + b.doubleValue();
    }
}
