package com.jsamuel.study.generic.gclass;

import lombok.Getter;
import lombok.Setter;

// 指定了两个泛型类型
@Getter
@Setter
public class NotePad<K, V> {

    // 变量类型由外部决定
    private K key;

    // 变量类型由外部决定
    private V value;

}
