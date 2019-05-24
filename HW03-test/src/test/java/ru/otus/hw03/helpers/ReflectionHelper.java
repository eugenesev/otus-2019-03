package ru.otus.hw03.helpers;

import ru.otus.hw03.resources.TestingContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionHelper {

    private TestingContext tc;
    private int testsCount;

    public ReflectionHelper(Class<?> testClass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        tc = new TestingContext(testClass);
        testsCount = tc.getTestMethods().length;
    }

    public Object getTestObject() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?>[] constructors = tc.getTestClass().getDeclaredConstructors();
        Constructor constructor = constructors[0];
        return constructor.newInstance();
    }

    public boolean runBeforeAll() {
        boolean status = false;
        for (Method method : tc.getBeforeAllMethods()) {
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

    public boolean runAfterAll() {
        boolean status = false;
        for (Method method : tc.getAfterAllMethods()) {
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

    public boolean runTest(int i, Object testObject) {
        boolean status = false;
        Method[] methods = tc.getTestMethods();
        try {
            methods[i].invoke(testObject);
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
        }
        return status;
    }

    public boolean runBefore(Object testObject) {
        boolean status = false;
        for (Method method : tc.getBeforeMethods()) {
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

    public boolean runAfter(Object testObject) {
        boolean status = false;
        for (Method method : tc.getAfterMethods()) {
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

    public int getTestsCount() {
        return testsCount;
    }
}
