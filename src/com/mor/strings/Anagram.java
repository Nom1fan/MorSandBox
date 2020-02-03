package com.mor.strings;

/**
 * An anagram is defined as a a word, phrase, or name formed by rearranging the letters of
 * another. For example: cat, act, and cta are all anagrams of cat.
 * Write a method that given a string str and a word, returns the number of anagrams of word
 * contained in str.
 * Example:
 * Input: str = “abcarcefjrca”, word=”car”
 * Output: 3
 */
public class Anagram {

    public static int calcNumOfAnagrams(String str, String word) {
        int cnt = 0;
        for (int i = 0; i + word.length() - 1 < str.length(); i++) {
            String wordFromStr = str.substring(i, i + word.length());
            boolean isAnagram = isAnagram(wordFromStr, word);
            if (isAnagram) {
                cnt++;
            }
        }
        return cnt;
    }

    private static boolean isAnagram(String wordA, String wordB) {
        boolean isAnagram = true;
        char[] wordAChars = wordA.toCharArray();
        for (int i = 0; i < wordB.length(); i++) {
            if (!isContained(wordB, wordAChars[i])) {
                isAnagram = false;
                break;
            }
        }
        return isAnagram;
    }

    private static boolean isContained(String word, char aChar) {
        return word.indexOf(aChar) != -1;
    }

    public static void main(String[] args) {
        System.out.println("Number of Anagrams of car in abcarcefjrca is:" + calcNumOfAnagrams("abcarcefjrca", "car"));
    }
}
