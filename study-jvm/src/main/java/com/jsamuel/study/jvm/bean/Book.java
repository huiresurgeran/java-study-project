package com.jsamuel.study.jvm.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Book {
    private static final Logger logger = LoggerFactory.getLogger(Book.class);

    public static void main(String[] args) {
        logger.info("Hello Book");
        test();
        //Book book = new Book();
    }

    public static void test() {
        logger.info("test Book");
    }

    Book(){
        logger.info("book constructor");
        logger.info("price {}, amount {}", price, amount);
    }

    {
        logger.info("book code block");
    }

    int price = 10;

    static {
        logger.info("book static code");
    }

    static int amount = 112;
}
