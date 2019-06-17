package ru.otus;

import java.lang.reflect.*;
import java.util.Arrays;


public class LogRunner {


    private final ClassInterface loggedObject;

    public LogRunner(ClassInterface loggedObject) {
        this.loggedObject = loggedObject;
    }

    public LogRunner(Class loggedObjectClass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor[] constructors = loggedObjectClass.getDeclaredConstructors();
        Constructor constructor = constructors[0];
        loggedObject = (ClassInterface) constructor.newInstance();
    }


    public ClassInterface log() {
        return LogRunner.log(loggedObject);

    }

    public static ClassInterface log(Object loggedObject) {
        InvocationHandler handler = new CustomInvocationHandler(loggedObject);
        return (ClassInterface) Proxy.newProxyInstance(loggedObject.getClass().getClassLoader(), new Class[]{ClassInterface.class}, handler);
    }


    private static class CustomInvocationHandler implements InvocationHandler {

        private final Object myClass;

        CustomInvocationHandler(Object loggedObject) {
            this.myClass = loggedObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object returnValue;

            if (method.isAnnotationPresent(LOG.class)) {
                System.out.print("invoking method:" + method + ", params: " + Arrays.toString(args));
                returnValue = method.invoke(myClass, args);
                System.out.println(" " + "Return value:" + returnValue);
                return returnValue;
            }
            else {return method.invoke(myClass, args);}
        }
    }
}
