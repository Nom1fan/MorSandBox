package com.mor.strings;

import java.util.LinkedHashSet;

/**
 * Given a string str of lowercase characters, the task is to remove duplicates and return a
 * resultant string without modifying the order of characters in the original string.
 * Examples:
 * Input: str = "geeksforgeeks"
 * Output: geksfor
 * <p>
 * Input: str = "characters"
 * Output: chartes
 */
public class RemoveDuplicates {

    public static String removeDuplicates(String str) {
        LinkedHashSet<Character> linkedHashSet = new LinkedHashSet<>();
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            linkedHashSet.add(aChar);
        }
        StringBuilder stringBuilder = new StringBuilder();
        linkedHashSet.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    public static String removeDuplicatesOnlyPrimitives(String str) {
        char[] chars = str.toCharArray();
        int[] visited = new int[26];

        StringBuilder noDupChars = new StringBuilder();

        for (char aChar : chars) {
            if (visited[aChar - 97] == 0) {
                visited[aChar - 97] = 1;
                noDupChars.append(aChar);
            }
        }
        return noDupChars.toString();
    }

    public static void main(String[] args) {
        System.out.println("Removing dups from geeksforgeeks is:" + removeDuplicates("geeksforgeeks"));
        System.out.println("Removing dups from characters is:" + removeDuplicates("characters"));

        System.out.println("Remove dups from geeksforgeeks with only primities:" + removeDuplicatesOnlyPrimitives("geeksforgeeks"));
        System.out.println("Remove dups from characters with only primities:" + removeDuplicatesOnlyPrimitives("characters"));
    }
}


