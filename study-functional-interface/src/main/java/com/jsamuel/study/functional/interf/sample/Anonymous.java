package com.jsamuel.study.functional.interf.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Anonymous {

    private static final Logger logger = LoggerFactory.getLogger(Anonymous.class);

    public static void main(String[] args) {
        // (params) -> expression
        // (params) -> statement
        // (params) -> { statements }
        new Thread(() -> logger.info("lambda expression, anonymous class")).start();
    }
}
