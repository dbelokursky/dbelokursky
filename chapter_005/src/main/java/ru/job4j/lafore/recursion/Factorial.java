package ru.job4j.lafore.recursion;

/**
 * @author Dmitry Belokursky
 * @since 18.11.17.
 */
public class Factorial {

    public static void main(String[] args) {
        System.out.println(factorial(16));
    }

    public static int factorial(int n) {
        return n == 0 ? 1 : n * factorial(n - 1);
    }
}
