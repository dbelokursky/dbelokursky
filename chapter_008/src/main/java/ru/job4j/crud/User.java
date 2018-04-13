package ru.job4j.crud;

import java.sql.Timestamp;

/**
 * @author Dmitry Belokursky
 * @since 08.04.18.
 */
public class User {

    private String name;

    private String login;

    private String email;

    private Timestamp createDate;

    public User(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = new Timestamp(System.currentTimeMillis());
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }
}
