package ru.job4j.carssale.service;

import ru.job4j.carssale.domain.Car;

import java.util.List;

public interface CarService {

    List<Car> findAll();

    Car findById(Long id);

    void save(Car car);
}
