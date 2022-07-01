package com.jsamuel.study.generic.Erase;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Erase {

    private static final Logger logger = LoggerFactory.getLogger(Erase.class);

    public static void main(String[] args) {
        testErase1();
        testErase2();
    }

    public static void testErase1() {
        // 验证原始类型相等

        ArrayList<String> strList = new ArrayList<String>();
        strList.add("123");

        ArrayList<Integer> intList = new ArrayList<Integer>();
        intList.add(123);

        logger.info("class compare result: {}", strList.getClass() == intList.getClass());
    }

    public static void testErase2() {
        // 通过反射添加其他类型元素

        ArrayList<Integer> intList = new ArrayList<Integer>();
        intList.add(1);

        try {
            intList.getClass().getMethod("add", Object.class).invoke(intList, "asdfg");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e){
            logger.error(e.getMessage());
        }

        for (int i = 0; i < intList.size(); i++) {
            logger.info("list {}: {}", i, intList.get(i));
        }
    }
}
