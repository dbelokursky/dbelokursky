package ru.job4j.application;

import java.util.UUID;

/**
 * Created by db on 01.07.17.
 */
public class Item {

    private String name;

    private String description;

    private Comment[] comments;

    private String id;

    public Item() {
    }


    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return this.name + " " + this.description + " " + "ID: " + this.id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Comment[] getComments() {
        return comments;
    }

    public void setComments(Comment[] comments) {
        this.comments = comments;
    }
}
