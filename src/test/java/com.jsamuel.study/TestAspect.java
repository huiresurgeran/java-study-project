package com.jsamuel.study;

import com.jsamuel.study.config.AopConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestAspect {

    @Test
    public void testWriterAspect() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);

        // 注意：不能自己创建对象，只有Spring容器中的bean，才能使用Sprint AOP提供的功能
        // WriterService writerService = new WriterService();
        // writerService.write();
        WriterService writerService = applicationContext.getBean(WriterService.class);
        writerService.write();

        applicationContext.close();
    }

    @Test
    public void testWriterAspectException() throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);

        WriterService writerService = applicationContext.getBean(WriterService.class);
        writerService.writeException();

        applicationContext.close();
    }
}
