package com.github.roman1306.practice;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Application {
    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> entityClass = Class.forName("com.github.roman1306.practice.entity.Entity");

        for (Method method : entityClass.getDeclaredMethods()) {
            System.out.println(method.getName());

            System.out.println("\tParameters:");
            for (Parameter parameter : method.getParameters()) {
                System.out.println("\t\t" + parameter.toString());
            }

            System.out.println("\tAnnotations:");
            for (Annotation annotation : method.getAnnotations()) {
                System.out.println("\t\t" + annotation.toString());
            }
            System.out.println("\t\tParameters Annotation:");
            for (AnnotatedType a: method.getAnnotatedParameterTypes()) {
                System.out.println("\t\t\t" + a.getType());
            }
        }
    }
}
