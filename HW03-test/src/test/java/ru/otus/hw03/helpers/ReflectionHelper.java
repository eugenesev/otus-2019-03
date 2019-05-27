package ru.otus.hw03.helpers;

import ru.otus.hw03.resources.TestingContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ReflectionHelper {

    private static int testsCount;
    private static TestingContext testContext;

    private ReflectionHelper(){
    }

    public static void useReflectionHelper(TestingContext testingContext) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        testContext = testingContext;
        testsCount = testContext.getTestMethods().length;
    }

    public static Object getTestObject() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?>[] constructors = testContext.getTestClass().getDeclaredConstructors();
        Constructor constructor = constructors[0];
        return constructor.newInstance();
    }

    public static boolean runBeforeAll() {
        boolean status = false;
        for (Method method : testContext.getBeforeAllMethods()) {
            System.out.println("-----Before All Tests-----");
            try {
                method.invoke(null);
                status = true;
            } catch (Exception e) {
                e.printStackTrace();
                status = false;
            }
        }
        return status;
    }

    public static boolean runAfterAll() {
        boolean status = false;
        for (Method method : testContext.getAfterAllMethods()) {
            System.out.println("-----After All Tests-----");
            try {
                method.invoke(null);
                status = true;
            } catch (Exception e) {
                e.printStackTrace();
                status = false;
            }
        }
        return status;
    }

    public static boolean runTest(int i, Object testObject) {
        boolean status = false;
        Method[] methods = testContext.getTestMethods();
        try {
            methods[i].invoke(testObject);
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
        }
        return status;
    }

    public static boolean runBefore(Object testObject) {
        boolean status = false;
        for (Method method : testContext.getBeforeMethods()) {
            try {
                method.invoke(testObject);
                status = true;
            } catch (Exception e) {
                e.printStackTrace();
                status = false;
            }
        }
        return status;
    }

    public static boolean runAfter(Object testObject) {
        boolean status = false;
        for (Method method : testContext.getAfterMethods()) {
            try {
                method.invoke(testObject);
                status = true;
            } catch (Exception e) {
                e.printStackTrace();
                status = false;
            }
        }
        return status;
    }

    public static int getTestsCount() {
        return testsCount;
    }
}
