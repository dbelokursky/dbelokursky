package ru.job4j.application;

/**
 * @author Dmitry Belokursky
 * @since 12.07.17.
 */
public abstract class BaseAction implements UserAction {

    private String name;

    private int key;

    BaseAction(String name, int key) {
        this.name = name;
        this.key = key;
    }

    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }

    public int key() {
        return this.key;
    }
}
