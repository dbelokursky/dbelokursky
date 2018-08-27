package ru.job4j.carssale.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.carssale.domain.Car;
import ru.job4j.carssale.repository.CarRepository;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findAll() {
        return Lists.newArrayList(carRepository.findAll());
    }

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).get();
    }
}
