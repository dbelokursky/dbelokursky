package ru.job4j.storage.dao;

import ru.job4j.storage.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    void add(User user);

    void update(User user);

    Optional<User> getById(int userId);

    void deleteById(int userId);

    List<User> getAll();
}
