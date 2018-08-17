package ru.job4j.carssale.dao;

import ru.job4j.carssale.models.Car;
import ru.job4j.carssale.models.Image;

import java.util.List;
import java.util.Set;

public interface CarDao {

    Car add(Car Car, Set<Image> images);

    Car update(Car car);

    Car getCar(int id);

    List<Car> getByBrand(String brand);

    List<Car> getByModel(String model);

    List<Car> getUnsold();

    List<Car> getWithImages();

    List<Car> getAll();
}
