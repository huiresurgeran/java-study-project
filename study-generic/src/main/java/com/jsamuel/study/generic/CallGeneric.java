package com.jsamuel.study.generic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CallGeneric {

    private static final Logger logger = LoggerFactory.getLogger(CallGeneric.class);

    public static void main(String[] args) {
        testCallGeneric();
    }

    public static void testCallGeneric() {
        // 不指定泛型
        // 两个参数都是Integer，所以T是Integer
        int i = add(1, 2);
        // 一个是Integer，一个是Float，取同一父类的最小级，Number
        Number f = add(1, 1.2);
        // 一个是Integer，一个是String，取同一父类的最小级，Object
        Object o = add(1, "123");
        logger.info("i: {}, f: {}, o: {}", i, f, o);

        // 指定泛型
        // 指定Integer，所以只能为Integer类型或者其子类
        int a = CallGeneric.<Integer>add(1, 2);
        // 编译错误，指定了Integer，不能有Float
        // int b = GenericTest.<Integer>add(1, 1.2);
        // 指定为Number，可以为Number的子类，Integer和Float都支持
        Number c = CallGeneric.<Number>add(1, 1.2);
        // 编译错误，指定了Integer，不能有String
        // int d = GenericTest.<Integer>add(1, "123");
        // 指定为Object，可以为Object的子类，Integer和String都支持
        Object e = CallGeneric.<Object>add(1, "123");
        logger.info("a: {}, c: {}, e: {}", a, c, e);
    }

    public static <T> T add(T x, T y) {
        return y;
    }

    /**
     * 通过反射实例化一个泛型
     *
     * @param clazz
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T newTClass(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T obj = clazz.newInstance();
        return obj;
    }

}
