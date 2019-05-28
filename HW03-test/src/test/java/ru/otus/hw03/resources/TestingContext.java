package ru.otus.hw03.resources;

import ru.otus.hw03.annotations.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestingContext {

    private Class testClass;
    private List<Method> beforeAllMethods;
    private List<Method> afterAllMethods;
    private List<Method> beforeMethods;
    private List<Method> afterMethods;
    private List<Method> testMethods;

    public TestingContext(Class<?> classTest) {
        this.testClass = classTest;
        fillContext();
    }

    public void fillContext() {
        beforeAllMethods = getAnnotatedMethods(BeforeAll.class);
        afterAllMethods = getAnnotatedMethods(AfterAll.class);
        beforeMethods = getAnnotatedMethods(Before.class);
        afterMethods = getAnnotatedMethods(After.class);
        testMethods = getAnnotatedMethods(Test.class);
    }

    public List<Method> getBeforeAllMethods() {
        return beforeAllMethods;
    }

    public List<Method> getAfterAllMethods() {
        return afterAllMethods;
    }

    public List<Method> getBeforeMethods() {
        return beforeMethods;
    }

    public List<Method> getAfterMethods() {
        return afterMethods;
    }

    public List<Method> getTestMethods() {
        return testMethods;
    }

    private List<Method> getAnnotatedMethods(Class<? extends Annotation> annotation) {
        List<Method> annotatedMethods = new ArrayList<>();
        Method[] declaredMethods = testClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(annotation))
                annotatedMethods.add(method);
        }
        return annotatedMethods;
    }
}

