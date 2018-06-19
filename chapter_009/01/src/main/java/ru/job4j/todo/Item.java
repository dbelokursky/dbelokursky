package ru.job4j.todo;

import java.sql.Timestamp;

/**
 * @author Dmitry Belokursky
 * @since 10.06.18.
 */
public class Item {

    private int id;

    private String description;

    private Timestamp created;

    private boolean done;

    public Item() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
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

        if (getId() != item.getId()) {
            return false;
        }
        if (isDone() != item.isDone()) {
            return false;
        }
        if (!getDescription().equals(item.getDescription())) {
            return false;
        }
        return getCreated().equals(item.getCreated());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getCreated().hashCode();
        result = 31 * result + (isDone() ? 1 : 0);
        return result;
    }
}
