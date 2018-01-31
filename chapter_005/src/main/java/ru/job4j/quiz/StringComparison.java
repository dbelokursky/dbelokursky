package ru.job4j.quiz;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitry Belokursky
 * @since 16.01.18.
 */
public class StringComparison {

    /**
     * Checking whether the first and the second string of the same symbols.
     * Case insensitive.
     *
     * @param first  string
     * @param second string
     * @return result. True/False.
     */
    public boolean containsAll(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }
        return sort(first).equals(sort(second));
    }

    public boolean containsAllWithArr(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }

        int codePoints = 65535; //utf-8
        int[] symbols = new int[codePoints];
        char[] firstArr = first.toLowerCase().toCharArray();

        for (char c : firstArr) {
            symbols[c]++;
        }

        for (int i = 0; i < second.length(); i++) {
            if (--symbols[second.charAt(i)] < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean containsAllWithMap(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }
        char[] firstArr = first.toLowerCase().toCharArray();
        char[] secondArr = second.toLowerCase().toCharArray();
        Map<Character, Integer> charMap = new HashMap<>();

        for (char c : firstArr) {
            int counter = 1;
            if (charMap.containsKey(c)) {
                counter = charMap.get(c) + 1;
            }
            charMap.put(c, counter);
        }

        for (char c : secondArr) {
            int counter = -1;
            if (charMap.containsKey(c)) {
                counter = charMap.get(c) - 1;
                charMap.put(c, counter);
            }
        }

        for (char c : charMap.keySet()) {
            if (charMap.get(c) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sorts characters of the string.
     *
     * @param s string
     * @return sorted string
     */
    private String sort(String s) {
        char[] symbols = s.toLowerCase().toCharArray();
        Arrays.sort(symbols);
        return new String(symbols);
    }
}
