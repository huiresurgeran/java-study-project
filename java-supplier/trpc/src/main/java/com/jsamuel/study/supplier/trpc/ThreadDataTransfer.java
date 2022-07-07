package com.jsamuel.study.supplier.trpc;

import com.jsamuel.study.supplier.log.Reporter;
import com.jsamuel.study.supplier.log.ThreadBean;
import java.util.Random;
import java.util.function.Supplier;

public class ThreadDataTransfer {

    public void report() {
        Supplier<ThreadBean> supplier = () -> {
            ThreadBean threadBean = new ThreadBean();
            threadBean.setVar1(new Random().nextInt());
            threadBean.setVar2(new Random().nextInt());
            return threadBean;
        };

        Reporter reporter = new Reporter(supplier);
    }
}
