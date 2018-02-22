package ru.job4j.cache;

/**
 * @author Dmitry Belokursky
 * @since 11.02.18.
 */
public class OptimisticException extends Exception {

    public OptimisticException(String message) {
        super(message);
    }
}
