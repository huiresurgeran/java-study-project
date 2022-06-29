package com.jsamuel.study.generic.ginterface;

// 在接口上定义泛型
public interface Info<T> {

    // 定义抽象方法，抽象方法的返回值是泛型类型
    public T getVar();
}
