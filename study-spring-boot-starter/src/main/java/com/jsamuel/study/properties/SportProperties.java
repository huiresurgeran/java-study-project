package com.jsamuel.study.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sport")
@Getter
@Setter
public class SportProperties {

    /**
     * 类型
     */
    private String type = "";

    /**
     * 名称
     */
    private String name = "";

    /**
     * 参与人数
     */
    private int count = 0;

    /**
     * 是否喜欢
     */
    private boolean like = false;
}
