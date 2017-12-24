package ru.job4j.bank;

/**
 * @author Dmitry Belokursky
 * @since 12.09.17.
 */
public class User implements Comparable<User> {

    private String name;

    private String passport;

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    @Override
    public int compareTo(User o) {
        return name.compareTo(o.getName());
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
        if (!getName().equals(user.getName())) {
            return false;
        }
        return getPassport().equals(user.getPassport());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getPassport().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", passport='" + passport + '\'' + '}';
    }
}
