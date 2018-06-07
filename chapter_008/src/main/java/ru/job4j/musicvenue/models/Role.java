package ru.job4j.musicvenue.models;

/**
 * @author Dmitry Belokursky
 * @since 13.05.18.
 */
public class Role extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role role = (Role) o;

        return getName().equals(role.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
