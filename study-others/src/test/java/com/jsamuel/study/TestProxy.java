package com.jsamuel.study;

import com.jsamuel.study.proxy.ReaderServiceImpl;
import com.jsamuel.study.proxy.ReaderServiceProxy;
import com.jsamuel.study.proxy.ReaderServiceProxyFactory;
import org.junit.Test;

public class TestProxy {

    @Test
    public void testStaticProxy() {
        // 目标对象
        ReaderService readerService = new ReaderServiceImpl();
        System.out.println("目标对象信息： " + readerService.getClass());

        // 代理对象
        ReaderServiceProxy proxy = new ReaderServiceProxy(readerService);
        System.out.println("代理对象信息: " + proxy.getClass());

        // 执行代理方法
        proxy.read();
    }

    @Test
    public void testDynamicProxy() {
        // 目标对象
        ReaderService readerService = new ReaderServiceImpl();
        System.out.println("目标对象信息： " + readerService.getClass());

        // 代理对象
        ReaderService proxy = (ReaderService) new ReaderServiceProxyFactory(readerService).getProxyInstance();
        System.out.println("代理对象信息: " + proxy.getClass());

        // 执行代理方法
        proxy.read();
    }

    @Test
    public void testCglibProxy() {
        // 目标对象
        WriterService writerService = new WriterService();
        System.out.println("目标对象信息： " + writerService.getClass());

        // 代理对象
        WriterService proxy = (WriterService) new WriterServiceProxyFactory(writerService).getProxyInstance();
        System.out.println("代理对象信息: " + proxy.getClass());

        // 执行代理方法
        proxy.write();
    }
}
