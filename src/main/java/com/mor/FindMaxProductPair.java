package com.mor;

import java.util.*;
import java.util.stream.Collectors;

public class FindMaxProductPair {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(-1, -10, 1, 10, null);
        System.out.println("Input collection is:" + Arrays.toString(integers.toArray()));
        Pair maxProductPair = findMaxProductPair(integers);
        System.out.println("Input collection after find is:" + Arrays.toString(integers.toArray()));
        System.out.println("Max product pair is:" + maxProductPair);
    }

    /**
     * Find the two integers in collection which produce max product and return them
     */
    public static Pair findMaxProductPair(Collection<Integer> collection) {
        if (collection == null || collection.isEmpty() || collection.size() == 1) {
            return null;
        }

        LinkedList<Integer> integerLinkedList = collection.stream().filter(Objects::nonNull).sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toCollection(LinkedList::new));
        integerLinkedList.remove(new Integer(1));

        if (integerLinkedList.size() == 2) {
            Iterator<Integer> iterator = collection.iterator();
            return new Pair(iterator.next(), iterator.next());
        }

        Integer min = integerLinkedList.pollFirst();
        Integer almostMin = integerLinkedList.pollFirst();
        Long minProduct = null;
        if (min != null && almostMin != null) {
            minProduct = (long) min * almostMin;
        }

        Integer max = integerLinkedList.pollLast();
        Integer almostMax = integerLinkedList.pollLast();
        Long maxProduct = null;
        if (max != null && almostMax != null) {
            maxProduct = (long) max * almostMax;
        }

        if (maxProduct != null && minProduct != null) {
            if (maxProduct > minProduct) {
                printMaxProductPair(max, almostMax, maxProduct);
                return new Pair(max, almostMax);
            } else {
                printMaxProductPair(min, almostMin, minProduct);
                return new Pair(min, almostMin);
            }
        } else if (maxProduct != null) {
            printMaxProductPair(max, almostMax, maxProduct);
            return new Pair(max, almostMax);
        } else {
            printMaxProductPair(min, almostMin, minProduct);
            return new Pair(min, almostMin);
        }
    }

    private static void printMaxProductPair(Integer max, Integer almostMax, Long maxProduct) {
        System.out.println(String.format("Max product is %d*%d=%d", max, almostMax, maxProduct));
    }

    public static class Pair {

        private int num1;
        private int num2;

        public Pair(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }

        public int getNum1() {
            return num1;
        }

        public int getNum2() {
            return num2;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "num1=" + num1 +
                    ", num2=" + num2 +
                    '}';
        }
    }
}
