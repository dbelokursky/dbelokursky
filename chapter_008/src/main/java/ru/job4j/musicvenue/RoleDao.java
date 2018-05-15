package ru.job4j.musicvenue;

import ru.job4j.musicvenue.models.Role;

import java.util.List;

/**
 * @author Dmitry Belokursky
 * @since 13.05.18.
 */
public interface RoleDao {

    boolean add(Role role);

    boolean update(int id, Role role);

    boolean delete(int id);

    Role findById(int id);

    List<Role> findAll();
}
