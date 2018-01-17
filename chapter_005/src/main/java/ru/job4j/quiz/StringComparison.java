package ru.job4j.quiz;

import java.util.Arrays;

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

    public boolean containsAllLinear(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }

        int codePoints = 1_112_064; //utf-8
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
