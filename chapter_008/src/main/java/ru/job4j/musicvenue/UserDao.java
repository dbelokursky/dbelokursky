package ru.job4j.musicvenue;

import ru.job4j.musicvenue.models.User;

import java.util.List;

/**
 * @author Dmitry Belokursky
 * @since 13.05.18.
 */
public interface UserDao {

    boolean add(User user);

    boolean update(int id, User user);

    boolean delete(int id);

    User findById(int id);

    List<User> findAll();
}
