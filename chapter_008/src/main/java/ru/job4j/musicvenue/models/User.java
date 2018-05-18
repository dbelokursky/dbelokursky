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
}
