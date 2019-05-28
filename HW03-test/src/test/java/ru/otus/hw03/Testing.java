package ru.otus.hw03;

import ru.otus.hw03.helpers.TestRunner;
import ru.otus.hw03.tests.FileRWTest;

import java.lang.reflect.InvocationTargetException;

public class Testing {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        //Запускаем тесты
        TestRunner.run(FileRWTest.class);
    }
}
