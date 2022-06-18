package com.jsamuel.study.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieService {

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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("type: ").append(type)
                .append(", name: ").append(name)
                .append(", year: ").append(year)
                .append(", like: ").append(like);
        return sb.toString();
    }
}
