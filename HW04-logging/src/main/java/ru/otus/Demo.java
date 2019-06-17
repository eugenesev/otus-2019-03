package ru.otus;

import java.lang.reflect.InvocationTargetException;

public class Demo {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        ClassInterface c1 = new ClassImpl();
        c1.calculation(6);
        c1.calculation(5,7);
// Можно использовать статический метод
        ClassInterface c2;
        c2 = LogRunner.log(c1);
        c2.calculation(7);
        c2.calculation(4,4);
// Можно передать в конструктор либо объект, либо класс логируемого объекта
        ClassInterface c3 = new ClassImpl();
        new LogRunner(c3).log().calculation(5);
        new LogRunner(c3).log().calculation(5,5);

        new LogRunner(ClassImpl.class).log().calculation(5,8);
        new LogRunner(ClassImpl.class).log().calculation(8);

    }
}
