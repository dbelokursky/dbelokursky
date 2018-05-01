package ru.job4j.crud.models;

/**
 * @author Dmitry Belokursky
 * @since 01.05.18.
 */
public class Role {

    private final String name;

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
