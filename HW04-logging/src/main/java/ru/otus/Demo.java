package ru.otus;

import java.lang.reflect.InvocationTargetException;

public class Demo {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        System.out.println("________Без логгера________");
        ClassInterface c1 = new ClassImpl();
        c1.calculation(6);
        c1.calculation(5,7);

        System.out.println("______С логгированием______");
        ClassInterface c3 = new ClassImpl();
        new LogRunner<>(c3).log().calculation(5);
        new LogRunner<>(c3).log().calculation(5,5);

        c3 = new LogRunner<>(c3).log();
        c3.calculation(7,9);

    }
}
