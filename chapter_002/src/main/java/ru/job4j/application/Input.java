package ru.job4j.application;

/**
 * @author Dmitry Belokursky
 * @since 05.07.17.
 */
public interface Input {

    String ask(String question);

    int ask(String question, int[] range);
}
