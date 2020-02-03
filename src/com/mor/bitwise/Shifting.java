package com.mor.bitwise;

public class Shifting {

    public static void main(String[] args) {
        int num = 1376796946;
        int maxBinaryGap = findMaxBinaryGap(num);
        System.out.println(String.format("Max binary gap in num:%d (Binary:%s) is:%d", num, Integer.toBinaryString(num), maxBinaryGap));
    }

    public static int findMaxBinaryGap(int num) {
        int maxBinaryGap = 0;

        //Trim leading zeroes from the right
        while (num % 2 == 0) {
            num = num >> 1;
        }

        while (num > 1) {
            int binaryGapCount = 0;
            num = num >> 1;
            // Count 0's until next 1
            while (num % 2 == 0) {
                binaryGapCount++;
                num = num >> 1;
            }

            if (binaryGapCount > maxBinaryGap) {
                maxBinaryGap = binaryGapCount;
            }
        }

        return maxBinaryGap;
    }
}
