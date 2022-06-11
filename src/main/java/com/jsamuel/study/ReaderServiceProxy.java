package com.jsamuel.study;

public class ReaderServiceProxy implements ReaderService {

    private ReaderService target;

    public ReaderServiceProxy(ReaderService target) {
        this.target = target;
    }

    @Override
    public void read() {
        // 扩展额外功能
        System.out.println("start read book");
        target.read();
        System.out.println("end read book");
    }
}
