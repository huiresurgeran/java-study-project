package com.jsamuel.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ReaderServiceProxyFactory {

    // 维护一个目标对象
    private Object target;

    public ReaderServiceProxyFactory(Object target) {
        this.target = target;
    }

    // 为目标对象生成代理对象
    public Object getProxyInstance() {
        // 返回一个指定接口的代理类实现，该接口可以将方法调用指派到指定的调用处理程序
        return Proxy.newProxyInstance(
                // 指定当前目标对象使用的类加载器，ClassLoader loader
                target.getClass().getClassLoader(),
                // 目标对象实现的接口的类型，Class<?>[] interfaces
                target.getClass().getInterfaces(),
                // 事件处理器，InvocationHandler h
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("start read book");

                        // 执行目标对象方法
                        Object returnValue = method.invoke(target, args);

                        System.out.println("end read book");

                        return null;
                    }
                }
        );
    }
}
