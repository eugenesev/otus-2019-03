package ru.otus.hw03;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestingContext {

    private Class testClass;
    private Method[] declaredMethods;

    TestingContext(Class<?>testClass){
        this.testClass=testClass;
    }

    public boolean invokeAnnotatedMethods (Class<? extends Annotation> annotation) throws Exception {
        boolean status = false;
        declaredMethods = testClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(annotation)) {
                System.out.println("Annotation @" + (annotation.getName().substring(annotation.getPackageName().length() + 1)) + " present");
                try {
                    method.invoke(null);
                    status = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    status = false;
                }
            }
        }
      return status;
    }

    public boolean invokeAnnotatedMethods (Class<? extends Annotation> annotation, Object testObject) throws Exception {
        boolean status = false;
        declaredMethods = testClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(annotation)) {
                System.out.println("Annotation @" + (annotation.getName().substring(annotation.getPackageName().length() + 1)) + " present");
                try {
                    method.invoke(testObject);
                    status = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    status = false;
                }
            }
        }
        return status;
    }

    public Object getTestObject () throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?>[] constructors = testClass.getDeclaredConstructors();
        Constructor constructor = constructors[0];
        return constructor.newInstance();
    }
}

