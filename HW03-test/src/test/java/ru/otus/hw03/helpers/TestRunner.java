package ru.otus.hw03.helpers;


import java.lang.reflect.InvocationTargetException;

public class TestRunner {

    public static void run(Class<?> testClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {

        System.out.println("---------------Testing " + testClass.toString() + "---------------");

        ReflectionHelper helper = new ReflectionHelper(testClass);

        if (helper.runBeforeAll()) {
            System.out.println(helper.getTestsCount());
            for (int i = 0; i < helper.getTestsCount(); i++) {
                Object testObj = helper.getTestObject();
                if (helper.runBefore(testObj)) {
                    helper.runTest(i, testObj);
                }
                helper.runAfter(testObj);
            }
        }
        helper.runAfterAll();
    }
}
