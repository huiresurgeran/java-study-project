package com.jsamuel.study.autoconfigure;

import com.jsamuel.study.properties.SportProperties;
import com.jsamuel.study.service.SportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 开启配置
// 用于定义配置类，可替换xml配置文件
// 被注解的类的内部，包含一个或多个@Bean注解的方法
// 这些方法会被Context扫描，用于构建bean定义，初始化Spring容器
// 要求：不可以是final类型，不可以是匿名类，嵌套的configuration必须是静态类
@Configuration
// 开启使用映射实体对象
// 使标注了@ConfigurationProperties注解的类生效，即将标注了@ConfigurationProperties注解的类注入进spring容器
@EnableConfigurationProperties(SportProperties.class)
// 当Spring Ioc容器内存在指定Class的条件
// 存在MovieService class时初始化该配置
@ConditionalOnClass(SportService.class)
// 指定的属性是否有指定的值
// 存在对应配置信息时初始化该配置类
@ConditionalOnProperty(
        // 存在配置前缀movie
        prefix = "sport",
        // 开启，只要配置的值是非false都有效
        value = "enabled",
        // 缺失检查，如果该属性为true，表示name或者value没有配置对应属性时也能注入
        matchIfMissing = true
)
public class SportAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(SportAutoConfiguration.class);

    // application配置文件映射前缀实体对象
    @Autowired
    private SportProperties sportProperties;

    // 创建MovieService实体bean
    @Bean
    // 当SpringIoc容器内不存在指定Bean的条件
    // 缺少MovieService实体bean时，初始化MovieService，并添加到Spring Ioc中
    @ConditionalOnMissingBean(SportService.class)
    public SportService sportService() {
        logger.info(">>>SportService not found, create new SportService bean.");
        SportService sportService = new SportService();
        sportService.setType(sportProperties.getType());
        sportService.setName(sportProperties.getName());
        sportService.setCount(sportProperties.getCount());
        sportService.setLike(sportProperties.isLike());
        return sportService;
    }
}
