package ru.otus.hw03.helpers;

import ru.otus.hw03.resources.TestingContext;

import static ru.otus.hw03.helpers.ReflectionHelper.*;

import java.lang.reflect.InvocationTargetException;

public class TestRunner {

    public static void run(Class<?> testClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {

        System.out.println("---------------Testing " + testClass.toString() + "---------------");

        TestingContext testingContext = new TestingContext(testClass);

        if (runStaticMethods(testingContext.getBeforeAllMethods())) {
            for (int i = 0; i < testingContext.getTestMethods().size(); i++) {
                Object testObj = getClassInstance(testClass);
                if (runMethods(testingContext.getBeforeMethods(),testObj)) {
                    runMethod(testingContext.getTestMethods().get(i), testObj);
                }
               runMethods(testingContext.getAfterMethods(),testObj);
            }
        }
        runStaticMethods(testingContext.getAfterAllMethods());
    }
}
