package com.jsamuel.study.supplier.trpc.test;

import com.jsamuel.study.supplier.trpc.ThreadDataTransfer;
import org.junit.Test;

public class ThreadDataTransferTest {

    @Test
    public void testReport() {
        ThreadDataTransfer transfer = new ThreadDataTransfer();
        transfer.report();
    }
}
