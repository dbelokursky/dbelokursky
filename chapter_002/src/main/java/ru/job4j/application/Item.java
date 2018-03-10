package ru.job4j.application;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by db on 01.07.17.
 */
public class Item {

    private String name;

    private String description;

    private Comment[] comments;

    public Item() {
    }


    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(getName(), item.getName())
                && Objects.equals(getDescription(), item.getDescription())
                && Arrays.equals(getComments(), item.getComments());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getName(), getDescription());
        result = 31 * result + Arrays.hashCode(getComments());
        return result;
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
