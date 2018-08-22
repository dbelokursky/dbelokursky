package ru.job4j.carssale.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.carssale.models.Car;

public interface CarRepository extends CrudRepository<Car, Integer> {

//    List<Car> findAllByBrand();

//    List<Car> findAllByModel();
}
