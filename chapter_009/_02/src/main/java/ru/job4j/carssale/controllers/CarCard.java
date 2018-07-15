package ru.job4j.carssale.controllers;

import ru.job4j.carssale.CarsStore;
import ru.job4j.carssale.models.Car;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CarCard extends HttpServlet {

    private final CarsStore carsStore = CarsStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Car car = carsStore.getCar(Integer.parseInt(req.getParameter("carId")));
        req.setAttribute("car", car);
        req.getRequestDispatcher("/WEB-INF/views/CarCard.jsp").forward(req, resp);
    }
}
