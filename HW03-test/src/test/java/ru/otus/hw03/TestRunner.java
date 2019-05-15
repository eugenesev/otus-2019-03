package ru.otus.hw03;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {

    static Object testObj;
    // Получаем методы задекларированные в классе-тесте
    static Method[] declaredMethods;
//--------

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        run(AnnotationTest.class);

    }

    private static void run(Class<?> testClass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
// Выводим название класса-теста и получаем его конструктор по умолчанию
        System.out.println("---------------Testing " + testClass.toString()+"---------------");
        Constructor<?>[] constructors = testClass.getDeclaredConstructors();
        Constructor constructor = constructors[0];
//--------

// Получаем методы задекларированные в классе-тесте
       declaredMethods = testClass.getDeclaredMethods();
//--------

// Вызываем все методы помеченные аннотацией BeforeAll
        findAnnotation(BeforeAll.class, null);
//--------


        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Test.class)) {
                testObj = constructor.newInstance();

            // Вызываем все методы помеченные аннотацией Before
                findAnnotation(Before.class, testObj);
            //--------

                System.out.println("Annotation @Test present");
                try {
                    method.invoke(testObj);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            // Вызываем все методы помеченные аннотацией After
                findAnnotation(After.class, testObj);
            //--------
            }
        }
//--------

// Вызываем все методы помеченные аннотацией AfterAll

        findAnnotation(AfterAll.class, null);

//--------

    }

    public static void findAnnotation(Class<? extends Annotation> annotationClass, Object testObj) throws IllegalAccessException {

        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(annotationClass)) {
                System.out.println("Annotation @"+(annotationClass.getName().substring(annotationClass.getPackageName().length()+1) )+" present");
                try {
                    method.invoke(testObj);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
