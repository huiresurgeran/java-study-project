package com.jsamuel.study.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "movie")
@Getter
@Setter
public class MovieProperties {

    /**
     * 类型
     */
    private String type = "";

    /**
     * 名称
     */
    private String name = "";

    /**
     * 上映年份
     */
    private int year = 0;

    /**
     * 是否喜欢
     */
    private boolean like = false;
}
