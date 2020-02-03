package com.mor;

public class Fibonacci {

    public static long calcRecursive(long num) {

        if (num <= 1) {
            return 1;
        }

        return calcRecursive(num - 1) + calcRecursive(num - 2);
    }

    public static long calcIterative(long num) {
        long sum = 0;

        if (num <= 1) {
            return 1;
        }

        long prev = 1;

        for (int i = 0; i <= num; i++) {
            long tmp = sum;
            sum = sum + prev;
            prev = tmp;
        }

        return sum;
    }

    public static void main(String[] args) {
        long numToFib = 10;

        long startTime = System.nanoTime();
        Fibonacci.calcRecursive(numToFib);
        long endTime = System.nanoTime();

        long durationForRecursive = (endTime - startTime);  //divide by 1000000 to get milliseconds

        startTime = System.nanoTime();
        ;
        Fibonacci.calcIterative(numToFib);
        endTime = System.nanoTime();

        long durationForIterative = (endTime - startTime);  //divide by 1000000 to get milliseconds

        if (durationForRecursive < durationForIterative) {
            System.out.println(String.format("Duration for recursive is better by %d nanos!", durationForIterative - durationForRecursive));
        } else {
            System.out.println(String.format("Duration for iterative is better by %d millis!", (durationForRecursive - durationForIterative) / 1000000));
        }
    }
}
