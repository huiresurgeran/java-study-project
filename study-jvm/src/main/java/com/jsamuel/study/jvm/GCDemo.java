package com.jsamuel.study.jvm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GCDemo {
    private static final Logger logger = LoggerFactory.getLogger(GCDemo.class);

    public static void main(String[] args) {
        // allocate 4M space
        byte[] b = new byte[4 * 1024 * 1024];
        logger.info("first allocate");

        // allocate 4M space
        b = new byte[4 * 1024 * 1024];
        logger.info("second allocate");

    }
}
