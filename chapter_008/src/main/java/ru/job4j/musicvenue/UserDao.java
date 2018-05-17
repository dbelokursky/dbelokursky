package ru.job4j.musicvenue;

import ru.job4j.musicvenue.models.User;

import java.util.List;

/**
 * @author Dmitry Belokursky
 * @since 13.05.18.
 */
public interface UserDao {

    void add(User user);

    void update(int id, User user);

    void delete(int id);

    User findById(int id);

    List<User> findAll();
}
