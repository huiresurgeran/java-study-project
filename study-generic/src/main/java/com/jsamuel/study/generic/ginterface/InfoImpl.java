package com.jsamuel.study.generic.ginterface;

// 定义泛型接口的子类
public class InfoImpl<T> implements Info<T> {

    // 定义属性
    private T var;

    public InfoImpl(T var) {
        this.var = var;
    }

    public void setVar(T var) {
        this.var = var;
    }

    public T getVar() {
        return var;
    }

}
