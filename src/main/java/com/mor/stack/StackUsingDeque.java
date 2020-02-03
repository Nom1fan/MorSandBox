package com.mor.stack;

import java.util.ArrayDeque;

public class StackUsingDeque {

    public static void main(String[] args) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        stack.addFirst(1);
        stack.addFirst(2);
        stack.addFirst(3);
        System.out.println("Pushed 1,2 and then 3 into stack, so stack is 3->2->1");

        System.out.println("Popping and printing...");

        while (!stack.isEmpty()) {
            System.out.println(stack.pollFirst());
        }
    }
}
