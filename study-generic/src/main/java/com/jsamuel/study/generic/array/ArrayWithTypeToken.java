package com.jsamuel.study.generic.array;

import java.lang.reflect.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayWithTypeToken<T> {

    private static final Logger logger = LoggerFactory.getLogger(ArrayWithTypeToken.class);

    private T[] array;

    public ArrayWithTypeToken(Class<T> type, int size) {
        array = (T[]) Array.newInstance(type, size);
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) {
        return array[index];
    }

    public T[] create() {
        return array;
    }

    public static void main(String[] args) {
        ArrayWithTypeToken<Integer> arrayWithTypeToken = new ArrayWithTypeToken<>(Integer.class, 100);
        Integer[] array1 = arrayWithTypeToken.create();

        arrayWithTypeToken.put(1, 1);
        logger.info("index 1: {}", arrayWithTypeToken.get(1));
        logger.info("index 1: {}", array1[1]);
    }
}
