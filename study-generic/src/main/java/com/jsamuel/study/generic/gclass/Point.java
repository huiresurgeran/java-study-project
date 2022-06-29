package com.jsamuel.study.generic.gclass;

// T, type的简称，也可以写成其他的标识符
public class Point<T> {

    // var的类型由T指定，即由外部指定
    private T var;

    // 返回值的类型，由外部决定
    public T getVar() {
        return var;
    }

    // 设置值的类型，由外部决定
    public void setVar(T var) {
        this.var = var;
    }
}
