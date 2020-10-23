package com.github.roman1306.practice.entity;

import com.github.roman1306.practice.annotation.Secured;

public class Entity {

    @Secured(number = 5)
    public void someMethod() {}

    @Secured(number = 27, string = "text")
    private void somePrivateMethod() {}
}
