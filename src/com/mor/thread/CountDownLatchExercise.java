package com.mor.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExercise {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        countDownLatch.countDown();

        Thread t1 = new Thread(() -> {
            countDownLatch.countDown();
            System.out.println("I am T1 !!! wohoo!");
        });
        Thread t2 = new Thread(() -> {
            countDownLatch.countDown();
            System.out.println("I am T2 !!! wohoo!");
        });
        Thread t3 = new Thread(() -> {
            countDownLatch.countDown();
            System.out.println("I am T3 !!! wohoo!");
        });

        t1.start();
        t2.start();
        t3.start();
    }

}
