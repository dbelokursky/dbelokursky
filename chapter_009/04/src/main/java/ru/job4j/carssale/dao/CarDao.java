package ru.job4j.carssale.dao;

import ru.job4j.carssale.models.Car;
import ru.job4j.carssale.models.Image;

import java.util.List;
import java.util.Set;

public interface CarDao {

    Car getCar(int id);

    Car add(Car car, Set<Image> images);

    Car update(Car car);

    List<Car> getByBrand(String brand);

    List<Car> getByModel(String model);

    List<Car> getUnsold();

    List<Car> getWithImages();

    List<Car> getAll();
}
