package com.jsamuel.study.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SportService {

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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("type: ").append(type)
                .append(", name: ").append(name)
                .append(", count: ").append(count)
                .append(", like: ").append(like);
        return sb.toString();
    }
}
