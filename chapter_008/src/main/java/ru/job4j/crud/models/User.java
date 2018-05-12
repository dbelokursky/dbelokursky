package ru.job4j.crud.models;

import java.sql.Timestamp;

/**
 * @author Dmitry Belokursky
 * @since 08.04.18.
 */
public class User {

    private int id;

    private String name;

    private String login;

    private String email;

    private String country;

    private String city;

    private Timestamp createDate;

    private String password;

    private Role role;

    public User(String name, String login, String email, String password, String country, String city) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = new Timestamp(System.currentTimeMillis());
        this.password = password;
        this.role = new Role("USER");
        this.country = country;
        this.city = city;
    }

    public User(String name, String login, String email, String password, String role, String country, String city) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = new Role(role);
        this.country = country;
        this.city = city;
    }

    public User(int id, String name, String login, String email, Timestamp createDate, String password, String role, String country, String city) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
        this.password = password;
        this.role = new Role(role);
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
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

    public Role getRole() {
        return role;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }
}
