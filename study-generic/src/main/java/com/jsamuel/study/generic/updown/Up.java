package com.jsamuel.study.generic.updown;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 限制泛型只能是数字类型
@Getter
@Setter
@ToString
public class Up<T extends Number> {

    private T var;

}
