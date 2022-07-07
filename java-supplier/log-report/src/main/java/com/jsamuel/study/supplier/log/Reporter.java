package com.jsamuel.study.supplier.log;

import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Reporter {

    private static final Logger logger = LoggerFactory.getLogger(Reporter.class);

    Supplier<ThreadBean> supplier;

    boolean start = false;

    public Reporter(Supplier<ThreadBean> supplier) {
        this.supplier = supplier;
        this.start = true;

        reportThreadInfo();
    }

    public void reportThreadInfo() {
        if (start) {
            for (int i = 0; i < 10; i++) {
                ThreadBean threadBean = supplier.get();
                logger.info("thread bean: [{}]", threadBean.toString());
            }
        }
    }

}
