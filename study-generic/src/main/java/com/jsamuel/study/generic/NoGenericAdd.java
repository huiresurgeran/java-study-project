package com.jsamuel.study.generic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoGenericAdd {

    private static final Logger logger = LoggerFactory.getLogger(NoGenericAdd.class);

    public static int add(int a, int b) {
        logger.info("{} + {} = {}", a, b, a + b);
        return a + b;
    }

    public static float add(float a, float b) {
        logger.info("{} + {} = {}", a, b, a + b);
        return a + b;
    }

    public static double add(double a, double b) {
        logger.info("{} + {} = {}", a, b, a + b);
        return a + b;
    }

}
