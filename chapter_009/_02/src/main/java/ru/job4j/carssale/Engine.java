package ru.job4j.carssale;

/**
 * @author Dmitry Belokursky
 * @since 27.06.18.
 */
public class Engine {

    private int id;

    private String name;

    public Engine() {
    }

    public Engine(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
