package ru.job4j.application;

/**
 * @author Dmitry Belokursky
 * @since 09.07.17.
 */
public interface UserAction {

    int key();

    String info();

    void execute(Input input);
}
