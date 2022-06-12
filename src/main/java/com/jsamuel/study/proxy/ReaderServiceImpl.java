package com.jsamuel.study.proxy;

import com.jsamuel.study.ReaderService;

public class ReaderServiceImpl implements ReaderService {

    @Override
    public void read() {
        System.out.println("read book");
    }
}
