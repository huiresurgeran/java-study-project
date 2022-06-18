package com.jsamuel.study.bean;

import com.jsamuel.study.annotation.Range;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Range(min = 1, max = 10)
    public String name;

    @Range(max = 10)
    public String city;

    @Range(min = 0, max = 100)
    public int age;
}
