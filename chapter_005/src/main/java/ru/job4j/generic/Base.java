package ru.job4j.generic;

/**
 * @author Dmitry Belokursky
 * @since 20.10.17.
 */
public abstract class Base {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return getId().equals(base.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
