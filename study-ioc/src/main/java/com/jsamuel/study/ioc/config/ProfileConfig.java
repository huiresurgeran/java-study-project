package com.jsamuel.study.ioc.config;

import java.time.ZoneId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan
public class ProfileConfig {

    private static final Logger logger = LoggerFactory.getLogger(ProfileConfig.class);

    @Bean
    @Profile("!test")
    ZoneId createZoneId() {
        logger.info("createZoneId");
        return ZoneId.systemDefault();
    }

    @Bean
    @Profile("test")
    ZoneId createZoneIdForTest() {
        logger.info("createZoneIdForTest");
        return ZoneId.of("America/New_York");
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ProfileConfig.class);
    }
}
