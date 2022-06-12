package com.jsamuel.study.config;

import com.jsamuel.study.WriterService;
import com.jsamuel.study.aspect.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {

    // 业务逻辑类加入容器
    @Bean
    public WriterService writerService () {
        return new WriterService();
    }

    // 切面类加入容器
    @Bean
    public LoggingAspect loggingAspect () {
        return new LoggingAspect();
    }
}
