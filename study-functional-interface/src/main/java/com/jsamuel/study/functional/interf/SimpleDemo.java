package com.jsamuel.study.functional.interf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleDemo {

    private static final Logger logger = LoggerFactory.getLogger(SimpleDemo.class);

    public static void main(String[] args) {
        // 用lambda表达式，表示接口的一个实现（java 8支持，之前使用匿名类)
        SimpleInterf simpleInterf = message -> logger.info("Hello {}", message);
        simpleInterf.sayMessage("Jsamuel");
    }
}
