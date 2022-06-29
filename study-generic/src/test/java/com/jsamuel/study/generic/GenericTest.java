package com.jsamuel.study.generic;

import com.jsamuel.study.generic.gclass.NotePad;
import com.jsamuel.study.generic.gclass.Point;
import com.jsamuel.study.generic.ginterface.Info;
import com.jsamuel.study.generic.ginterface.InfoImpl;
import com.jsamuel.study.generic.method.Generic;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericTest {

    private static final Logger logger = LoggerFactory.getLogger(GenericTest.class);

    @Test
    public void testGenericAdd() {
        int a = 10;
        int b = 20;
        logger.info("add int result: {}", GenericAdd.add(a, b));

        double ad = 10.1;
        double bd = 20.5;
        logger.info("add double result: {}", GenericAdd.add(ad, bd));

        float af = 3.5f;
        float bf = 30.5f;
        logger.info("add float result: {}", GenericAdd.add(af, bf));
    }

    @Test
    public void testPoint() {
        Point<String> pointStr = new Point<String>();
        pointStr.setVar("test point str");
        logger.info("length: {}", pointStr.getVar().length());
    }

    @Test
    public void testNotePad() {
        NotePad<String, Integer> notePad = new NotePad<String, Integer>();
        notePad.setKey("tom");
        notePad.setValue(20);
        logger.info("name: {}, old: {}", notePad.getKey(), notePad.getValue());

    }

    @Test
    public void testInfo() {
        // 声明接口类型，通过子类实例化对象
        Info<String> info = new InfoImpl<String>("tom");
        logger.info("name: {}", info.getVar());
    }

    @Test
    public void testGeneric() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Generic generic = new Generic();
        // 调用泛型方法
        // obj，User类的实例
        // 利用Class.forName，指定泛型的具体类型
        Object obj = generic.getObject(Class.forName("com.jsamuel.study.generic.gclass.Point"));
    }
}
