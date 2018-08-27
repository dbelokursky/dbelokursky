package ru.job4j.carssale.service;

import ru.job4j.carssale.domain.Owner;

import java.util.List;

public interface OwnerService {

    Owner findAllByLoginAndPassword(String login, String password);

    Owner findByLogin(String login);

    List<Owner> findAll();
}

