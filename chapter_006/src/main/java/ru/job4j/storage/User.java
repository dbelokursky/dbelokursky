package ru.job4j.storage;

import java.util.Objects;

/**
 * @author Dmitry Belokursky
 * @since 26.01.18.
 */
public class User {

    private final int id;

    private volatile int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return getId() == user.getId() && getAmount() == user.getAmount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAmount());
    }

    @Override
    public String toString() {
        return "User{ id=" + id + ", amount=" + amount + '}';
    }
}
