package ru.otus.hw03.helpers;

import ru.otus.hw03.resources.TestingContext;

import static ru.otus.hw03.helpers.ReflectionHelper.*;

import java.lang.reflect.InvocationTargetException;

public class TestRunner {

    public static void run(Class<?> testClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {

        System.out.println("---------------Testing " + testClass.toString() + "---------------");

        useReflectionHelper(new TestingContext(testClass));

        if (runBeforeAll()) {
            System.out.println(getTestsCount());
            for (int i = 0; i < getTestsCount(); i++) {
                Object testObj = getTestObject();
                if (runBefore(testObj)) {
                    runTest(i, testObj);
                }
               runAfter(testObj);
            }
        }
        runAfterAll();
    }
}
