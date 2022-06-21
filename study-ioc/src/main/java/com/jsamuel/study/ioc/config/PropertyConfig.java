package com.jsamuel.study.ioc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("app.properties")
@Configuration
@ComponentScan
public class PropertyConfig {

    private static final Logger logger = LoggerFactory.getLogger(PropertyConfig.class);

    @Value("${app.zone:Z}")
    String zoneId;

    @Value("${app.server}")
    String server;

    @Value("${app.service:default-service}")
    String service;

    @Value("${app.ip}")
    String ip;

    @Bean
    String createLogger() {
        String message = String.format("zoneId:%s, server:%s, service:%s, ip:%s", zoneId, server, service, ip);
        logger.info(message);
        return message;
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(PropertyConfig.class);
    }
}
