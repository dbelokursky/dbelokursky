package ru.job4j.carssale.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.carssale.domain.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findAllByLoginAndPassword(String login, String password);

    Owner findByLogin(String login);
}
