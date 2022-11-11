package com.banlaptop.shop.dto;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Generic <T> {

    private final Class<T> clazz;

    public Generic(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void someFunction()
    {
        Field[] attributes =  this.clazz.getDeclaredFields();
        Arrays.stream(attributes).forEach(i-> System.out.println(i.getName()));
    }
}
