package ru.otus;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

public class LogRunner {

    public static ClassInterface log(ClassInterface classImpl) {
        InvocationHandler handler = new CustomInvocationHandler(classImpl);
        ClassInterface c2 = (ClassInterface) Proxy.newProxyInstance(Demo.class.getClassLoader(), new Class[]{ClassInterface.class}, handler);
        return c2;
    }


    static class CustomInvocationHandler implements InvocationHandler {

        private final ClassInterface myClass;

        CustomInvocationHandler(ClassInterface myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            int paramCount = method.getParameterCount();
            TypeVariable<Method>[] typeParam = method.getTypeParameters();
            System.out.println(typeParam.toString());

            System.out.println("invoking method:" + method + ", params: " + (int) args[0]);
            return method.invoke(myClass, args);
        }
    }

}
