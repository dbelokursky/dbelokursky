package ru.job4j.carssale.controllers;

import com.google.gson.Gson;
import ru.job4j.carssale.CarsStore;
import ru.job4j.carssale.models.Car;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarsToJsonHelper extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Car> cars = new CarsStore().getAll();

        resp.setContentType("application/json");
        resp.setContentType("UTF-8");
        resp.getWriter().write(new Gson().toJson(cars));
    }

    private Set<String> getBrands(List<Car> cars) {
        Set<String> brands = new HashSet<>();
        for (Car car : cars) {
            brands.add(car.getBrand());
        }
        return brands;
    }
}
