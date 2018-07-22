package ru.job4j.carssale;

import org.junit.Test;
import ru.job4j.carssale.models.Car;
import ru.job4j.carssale.models.Image;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CarsStoreTest {

    @Test
    public void add() {
        CarsStore carsStore = new CarsStore();
        Car car = new Car();
        Set<Image> images = new HashSet<>();
        car.setModel("testModel");
        car.setBrand("testBrand");
        car.setImages(images);

        carsStore.add(car, images);

        assertThat(car, is(carsStore.getCar(car.getId())));
    }

    @Test
    public void update() {
        CarsStore carsStore = new CarsStore();
        Car car = new Car();
        Set<Image> images = new HashSet<>();
        car.setSold(false);
        car.setModel("testModel");
        car.setBrand("testBrand");
        car.setImages(images);

        carsStore.add(car, images);
        car.setSold(true);
        carsStore.update(car);

        boolean expected = true;
        boolean result = carsStore.getCar(car.getId()).isSold();
        assertThat(result, is(expected));
    }

    @Test
    public void getAll() {
        CarsStore carsStore = new CarsStore();
        Car car1 = new Car();
        Set<Image> images = new HashSet<>();
        car1.setSold(false);
        car1.setModel("testModel");
        car1.setBrand("testBrand");
        car1.setImages(images);
        Car car2 = new Car();
        car2.setSold(false);
        car2.setModel("testModel2");
        car2.setBrand("testBrand2");
        car2.setImages(images);

        carsStore.add(car1, images);
        carsStore.add(car2, images);

        int expected = 2;
        int result = carsStore.getAll().size();
        assertThat(result, is(expected));
    }

    @Test
    public void getCar() {
        CarsStore carsStore = new CarsStore();
        Car car = new Car();
        Set<Image> images = new HashSet<>();
        car.setSold(false);
        car.setModel("testModel4");
        car.setBrand("testBrand4");
        car.setImages(images);

        carsStore.add(car, images);

        String expected = "testModel4";
        String result = carsStore.getCar(car.getId()).getModel();
        assertThat(result, is(expected));

    }
}