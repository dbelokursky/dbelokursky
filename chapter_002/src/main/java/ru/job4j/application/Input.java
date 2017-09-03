package ru.job4j.application;

import java.util.ArrayList;

/**
 * @author Dmitry Belokursky
 * @since 05.07.17.
 */
public interface Input {

    String ask(String question);

    int ask(String question, ArrayList<Integer> range);
}
