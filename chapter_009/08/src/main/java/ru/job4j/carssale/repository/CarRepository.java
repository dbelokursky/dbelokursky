package ru.job4j.carssale.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.carssale.domain.Car;

public interface CarRepository extends CrudRepository<Car, Long> {
}
