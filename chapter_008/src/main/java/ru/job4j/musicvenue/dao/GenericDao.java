package ru.job4j.musicvenue.dao;

import ru.job4j.musicvenue.models.BaseEntity;

import java.util.List;

public interface GenericDao<T extends BaseEntity> {

    void create(T object);

    void update(int id, T object);

    void delete(int id);

    T findById(int id);

    List<T> findAll();
}
