package com.jsamuel.study.functional.interf.sample;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunctionalReference {

    private static final Logger logger = LoggerFactory.getLogger(FunctionalReference.class);

    public static void main(String[] args) {
        // 构造引用
        Supplier<String> s = String::new;

        // 对象::实例方法 Lambda表达式的（形参列表）与实例方法（实参列表）类型，个数是对应的
        List list = Arrays.asList("piano", "wiolin", "guitar");
        list.forEach(System.out::println);

        // 类名::静态方法
        Stream<Double> stream = Stream.generate(Math::random);
        stream.forEach(System.out::println);

        // 类名::实例方法
        TreeSet<String> set = new TreeSet<>(String::compareTo);
    }
}
