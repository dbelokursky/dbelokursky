package ru.job4j.carssale.controllers;

import ru.job4j.carssale.CarsStore;
import ru.job4j.carssale.models.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CarEdit extends HttpServlet {

    private final CarsStore carsStore = new CarsStore();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        Transmission transmission = new Transmission();
        transmission.setName(req.getParameter("transmission"));
        Suspension suspension = new Suspension();
        suspension.setName(req.getParameter("suspension"));
        Engine engine = new Engine();
        engine.setName(req.getParameter("engine"));
        boolean status = Boolean.parseBoolean(req.getParameter("sold"));
        Owner owner = (Owner) req.getSession().getAttribute("owner");
        Car car = new Car();
        car.setId(id);
        car.setBrand(brand);
        car.setModel(model);
        car.setTransmission(transmission);
        car.setSuspension(suspension);
        car.setEngine(engine);
        car.setSold(status);
        car.setOwner(owner);
        carsStore.update(car);
        resp.sendRedirect(String.format("%s/cars", req.getContextPath()));
    }
}
