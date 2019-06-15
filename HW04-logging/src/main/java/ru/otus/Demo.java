package ru.otus;

import java.lang.reflect.Proxy;

public class Demo {
    public static void main(String[] args) {
       ClassInterface c1 = new ClassImpl();
       c1.calculation(6);
       LogRunner.log(c1).calculation(6);
    }
}
