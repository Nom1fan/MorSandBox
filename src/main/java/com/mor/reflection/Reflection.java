package com.mor.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {

    public static void accessPrivateMethod() throws InvocationTargetException, IllegalAccessException {

        JustAClass justAClass = new JustAClass();
        Class<? extends JustAClass> aClass = justAClass.getClass();
        Method[] methods = aClass.getDeclaredMethods();
        methods[0].setAccessible(true);
        methods[0].invoke(justAClass);

    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Reflection.accessPrivateMethod();
    }

    private static class JustAClass {

        private void foo() {
            System.out.println("I am fooing!");
        }
    }
}
