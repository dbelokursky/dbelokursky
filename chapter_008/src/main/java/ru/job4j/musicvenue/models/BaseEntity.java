package ru.job4j.musicvenue.models;

public abstract class BaseEntity {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}