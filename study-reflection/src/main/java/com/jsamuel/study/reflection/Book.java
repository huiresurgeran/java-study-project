package com.jsamuel.study.reflection;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Book {

    private String name = "";

    private int price = 0;

    private boolean sold = false;
}
