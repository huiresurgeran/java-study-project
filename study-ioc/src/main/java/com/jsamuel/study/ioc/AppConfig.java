package com.jsamuel.study.ioc;

import com.jsamuel.study.ioc.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfig {

    public static void main(String[] args) {
        // 独立的应用上下文
        // 创建Spring的IOC容器实例
        // 接收带注解的类作为输入
        // 让Spring容器创建并且装配好，配置文件中指定的所有的Bean
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        userService.login();
        userService.register();
    }
}
