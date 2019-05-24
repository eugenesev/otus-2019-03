package ru.otus.hw03.resources;

import ru.otus.hw03.annotations.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestingContext {

    private Class testClass;
    private Method[] beforeAllMethods;
    private Method[] afterAllMethods;
    private Method[] beforeMethods;
    private Method[] afterMethods;
    private Method[] testMethods;

    public TestingContext(Class<?> testClass) {
        this.testClass = testClass;
        beforeAllMethods = getAnnotatedMethods(BeforeAll.class);
        afterAllMethods = getAnnotatedMethods(AfterAll.class);
        beforeMethods = getAnnotatedMethods(Before.class);
        afterMethods = getAnnotatedMethods(After.class);
        testMethods = getAnnotatedMethods(Test.class);
    }

    public Class getTestClass() {
        return testClass;
    }

    public Method[] getBeforeAllMethods() {
        return beforeAllMethods;
    }

    public Method[] getAfterAllMethods() {
        return afterAllMethods;
    }

    public Method[] getBeforeMethods() {
        return beforeMethods;
    }

    public Method[] getAfterMethods() {
        return afterMethods;
    }

    public Method[] getTestMethods() {
        return testMethods;
    }

    private Method[] getAnnotatedMethods(Class<? extends Annotation> annotation) {
        List<Method> annotated = new ArrayList<>();
        Method[] annotatedMethods = new Method[0];
        Method[] declaredMethods = testClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(annotation))
                annotated.add(method);
            //else continue;
        }
        return annotated.toArray(annotatedMethods);
    }
}

