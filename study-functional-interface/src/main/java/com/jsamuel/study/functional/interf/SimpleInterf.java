package com.jsamuel.study.functional.interf;

@FunctionalInterface
public interface SimpleInterf {

    // 抽象方法
    void sayMessage(String message);

    // default方法不是抽象方法
    public default void defaultMethod() {

    }

    // static不是抽象方法
    public static void staticMethod() {

    }

    // java.lang.Object中的方法不是抽象方法
    public boolean equals(Object var1);
    
}
