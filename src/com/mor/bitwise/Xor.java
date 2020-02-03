package com.mor.bitwise;

public class Xor {

    public static void main(String[] args) {
        int num1 = 101;
        int num2 = 44;
        int xor = 101 ^ 44;
        int unxor = xor ^ 44;
        System.out.println(String.format("num1:%d, num2:%d, xor(%d,%d):%d, xor(%d,%d):%d", num1, num2, num1, num2, xor, xor, num2, unxor));
    }
}
