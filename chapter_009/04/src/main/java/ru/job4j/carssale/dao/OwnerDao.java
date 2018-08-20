package ru.job4j.carssale.dao;

import ru.job4j.carssale.models.Owner;

public interface OwnerDao {

    Owner isExist(String login, String password);
}
