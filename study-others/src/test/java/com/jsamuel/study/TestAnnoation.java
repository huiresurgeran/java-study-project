package com.jsamuel.study;

import com.jsamuel.study.annotation.Range;
import com.jsamuel.study.bean.Person;
import java.lang.reflect.Field;
import org.junit.Test;

public class TestAnnoation {

    @Test
    public void noParamPerson() {
        Person person = new Person();
        try {
            checkRange(person);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void IllegalNamePerson() {
        Person person = new Person("totototototo", "sz", 27);
        try {
            checkRange(person);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void IllegalCityPerson() {
        Person person = new Person("totoroyang", "shenzhen city", 27);
        try {
            checkRange(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkRange(Person person) throws IllegalAccessException {
        // 遍历Field
        for (Field field : person.getClass().getFields()) {
            // 获取Field上的Range注解
            Range range = field.getAnnotation(Range.class);
            if (range != null) {
                // 获取Field的值
                Object value = field.get(person);
                if (value instanceof String) {
                    String str = (String) value;
                    if (str.length() < range.min() || str.length() > range.max()) {
                        throw new IllegalArgumentException(
                                "Invalid field, name: " + field.getName() + ", type: " + field.getType());
                    }
                } else if (value instanceof Integer) {
                    Integer i = (Integer) value;
                    if (i < range.min() || i > range.max()) {
                        throw new IllegalArgumentException(
                                "Invalid field, name: " + field.getName() + ", type: " + field.getType());
                    }
                } else if (value == null) {
                    throw new IllegalArgumentException(
                            "Invalid field is null, name: " + field.getName() + ", type: " + field.getType());
                }
            }
        }
    }

    @Test
    public void IllegalAgePerson() {
        Person person = new Person("totoroyang", "sz", -5);
        try {
            checkRange(person);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
