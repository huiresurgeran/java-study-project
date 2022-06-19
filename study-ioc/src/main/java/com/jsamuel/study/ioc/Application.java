package com.jsamuel.study.ioc;

import com.jsamuel.study.ioc.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {
        // 创建Spring的IOC容器实例
        // 加载配置文件
        // 让Spring容器创建并且装配好，配置文件中指定的所有的Bean
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        // 从Spring容器中取出装配好的Bean
        // 根据Bean的类型
        UserService userService = context.getBean(UserService.class);
        // 根据Bean的ID
        UserService userService1 = (UserService) context.getBean("userService");

        userService.login();
        userService.register();

        userService1.login();
        userService1.register();
    }
}
