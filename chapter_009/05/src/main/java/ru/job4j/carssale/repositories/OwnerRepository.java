package ru.job4j.carssale.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.carssale.models.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Integer> {

    Owner findAllByLoginAndPassword(String login, String password);
}
