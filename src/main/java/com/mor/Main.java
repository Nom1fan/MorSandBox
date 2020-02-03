package com.mor;

import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {

        TreeMap<String, Integer> stringObjectTreeMap = new TreeMap<>(String::compareToIgnoreCase);

        stringObjectTreeMap.put("Ecd", 3);
        stringObjectTreeMap.put("Abc", 1);
        stringObjectTreeMap.put("B", 2);

        for (Integer value : stringObjectTreeMap.values()) {
            System.out.println(value);
        }
    }

}
