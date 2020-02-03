package com.mor;

import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Sets {

    public static void main(String[] args) {
        NavigableSet<String> stringNavigableSet = new TreeSet<>(Arrays.asList("Apple", "Mango", "Banana", "Eggplant"));
        System.out.println("Tree set:" + stringNavigableSet);

        NavigableSet<String> descendingSet = stringNavigableSet.descendingSet();
        System.out.println("Descending set:" + descendingSet);
    }
}
