package com.mor;

public class PrimeChecker {

    public static boolean isPrime(long num) {

        if (num <= 3) {
            return num > 1;
        }
        if (isEven(num)) {
            return false;
        }

        for (long i = 5; i * i < num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

    private static boolean isEven(long num) {
        return num % 2 == 0;
    }
}
