package com.jsamuel.study;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class WriterServiceProxyFactory implements MethodInterceptor {

    // 维护一个目标对象
    private Object target;

    public WriterServiceProxyFactory(Object target) {
        this.target = target;
    }

    // 为目标对象生成代理对象
    public Object getProxyInstance() {
        // 工具类
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(target.getClass());
        // 设置回调函数
        enhancer.setCallback(this);
        // 创建子类对象代理
        return enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("begin write");
        // 执行目标对象的方法
        Object returnValue = method.invoke(target, args);
        System.out.println("end write");
        return null;
    }
}
