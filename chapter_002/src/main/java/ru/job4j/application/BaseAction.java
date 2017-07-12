package ru.job4j.application;

/**
 * @author Dmitry Belokursky
 * @since 12.07.17.
 */
public abstract class BaseAction implements UserAction {

    private String name;

    private int key;

    BaseAction() {

    }

    BaseAction(String name, int key) {
        this.name = name;
        this.key = key;
    }

    public String info(int key, String name) {
        return String.format("%s. %s", key, name);
    }
}
