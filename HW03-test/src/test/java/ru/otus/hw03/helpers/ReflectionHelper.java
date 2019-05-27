package ru.otus.hw03.helpers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public final class ReflectionHelper {

    private ReflectionHelper() {
    }

    public static Object getClassInstance(Class clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        Constructor constructor = constructors[0];
        return constructor.newInstance();
    }

    public static boolean runStaticMethods(List<Method> methods) {
        boolean status = false;
        for (Method method : methods) {
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

    public static boolean runMethod(Method method, Object testObject) {
        boolean status = false;
        try {
            method.invoke(testObject);
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static boolean runMethods(List<Method> methods, Object testObject) {
        boolean status = false;
        for (Method method : methods) {
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
}
