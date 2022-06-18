package com.jsamuel.study;

import com.jsamuel.study.annotation.MetricTime;
import org.springframework.stereotype.Component;

@Component
public class WriterService {

    public void write() {
        System.out.println("write book");
    }

    public void writeException() throws Exception {
        System.out.println("write book error");
        throw new Exception("write book error");
    }

    @MetricTime("write")
    public void writeAnnotation() {
        System.out.println("write book with annotation");
    }
}
