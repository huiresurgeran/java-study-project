package com.jsamuel.study;

public class WriterService {

    public void write() {
        System.out.println("write book");
    }

    public void writeException() throws Exception {
        System.out.println("write book error");
        throw new Exception("write book error");
    }
}
