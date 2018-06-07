package ru.job4j.musicvenue.models;

/**
 * @author Dmitry Belokursky
 * @since 13.05.18.
 */
public class User extends BaseEntity {

    private String login;

    private String password;

    private int roleId;

    private int addressId;

    public User() {
    }

    public User(String login, String password, int roleId, int addressId) {
        this.login = login;
        this.password = password;
        this.roleId = roleId;
        this.addressId = addressId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
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

        if (getRoleId() != user.getRoleId()) {
            return false;
        }
        if (getAddressId() != user.getAddressId()) {
            return false;
        }
        if (!getLogin().equals(user.getLogin())) {
            return false;
        }
        return getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        int result = getLogin().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getRoleId();
        result = 31 * result + getAddressId();
        return result;
    }
}
