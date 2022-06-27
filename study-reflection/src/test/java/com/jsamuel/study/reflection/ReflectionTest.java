package com.jsamuel.study.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionTest {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void testNew() {
        Book book = new Book();
        book.setName("new book");
        book.setPrice(10);
        logger.info(book.toString());
    }

    @Test
    public void testReflection() {
        try {
            // get class
            Class cla = Class.forName("com.jsamuel.study.reflection.Book");

            // get method
            Method methodForName = cla.getMethod("setName", String.class);

            // get method
            Method methodForPrice = cla.getMethod("setPrice", int.class);

            // get method
            Method methodForStr = cla.getMethod("toString");

            // get constructor
            Constructor constructor = cla.getConstructor();

            // get new instance
            Object obj = constructor.newInstance();

            // invoke method
            methodForName.invoke(obj, "reflection book");
            methodForPrice.invoke(obj, 20);
            logger.info(methodForStr.invoke(obj).toString());
        } catch (ClassNotFoundException e) {
            logger.error("get class failed, ", e);
        } catch (NoSuchMethodException | SecurityException e) {
            logger.error("get method failed or get constructor failed, ", e);
        } catch (InstantiationException | IllegalAccessException |
                IllegalArgumentException | InvocationTargetException e) {
            logger.error("get instance failed or invoke failed, ", e);
        }
    }

    /**
     * 获取类的属性，不能获取私有属性
     */
    @Test
    public void testGetFields(){
        Class cla = Book.class;
        Field[] fields = cla.getFields();
        logger.info(">>> testGetFields >>>");
        for(Field field : fields){
            logger.info(field.getName());
        }
    }

    /**
     * 获取类的属性，包括私有属性
     */
    @Test
    public void testGetDeclaredFields(){
        Class cla = Book.class;
        Field[] fields = cla.getDeclaredFields();
        cla.getDeclaredMethods();
        cla.getDeclaredConstructors();
        logger.info(">>> testGetDeclaredFields >>>");
        for(Field field : fields){
            logger.info(field.getName());
        }
    }

    /**
     * 获取类的方法，不能获取私有方法
     */
    @Test
    public void testGetMethods(){
        Class cla = Book.class;
        Method[] methods = cla.getMethods();
        logger.info(">>> testGetMethods >>>");
        for(Method method : methods){
            logger.info(method.getName());
        }
    }

    /**
     * 获取类的方法，包括私有方法
     */
    @Test
    public void testGetDeclaredMethods(){
        Class cla = Book.class;
        Method[] methods = cla.getDeclaredMethods();
        logger.info(">>> testGetDeclaredMethods >>>");
        for(Method method : methods){
            logger.info(method.getName());
        }
    }

    /**
     * 获取类的构造器，不能获取私有构造器
     */
    @Test
    public void testGetConstructors(){
        Class cla = Book.class;
        Constructor[] constructors = cla.getConstructors();
        logger.info(">>> testGetConstructors >>>");
        for(Constructor constructor : constructors){
            logger.info(constructor.getName());
        }
    }

    /**
     * 获取类的构造器，包括私有构造器
     */
    @Test
    public void testGetDeclaredConstructors(){
        Class cla = Book.class;
        Constructor[] constructors = cla.getDeclaredConstructors();
        logger.info(">>> testGetDeclaredConstructors >>>");
        for(Constructor constructor : constructors){
            logger.info(constructor.getName());
        }
    }
}
