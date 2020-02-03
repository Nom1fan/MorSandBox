package com.mor.design.patterns;

/**
 * Lazy singleton which is also thread-safe
 */
public class Singleton {

    private Singleton() {
        System.out.println("I AM ALIVE !!!!");
    }

    public static Singleton getInstance() {
        return Inner.SINGLETON;
    }

    public static void main(String[] args) {
        System.out.println("Checking if lazy...");
        Singleton instance = Singleton.getInstance();
    }

    private static class Inner {

        private static final Singleton SINGLETON = new Singleton();
    }
}
