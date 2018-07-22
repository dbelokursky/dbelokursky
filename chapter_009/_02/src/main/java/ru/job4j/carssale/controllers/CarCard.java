package ru.job4j.carssale.controllers;

import ru.job4j.carssale.CarsStore;
import ru.job4j.carssale.models.Car;
import ru.job4j.carssale.models.Owner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CarCard extends HttpServlet {

    private final CarsStore carsStore = new CarsStore();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Car car = carsStore.getCar(Integer.parseInt(req.getParameter("carId")));
        req.setAttribute("car", car);
        HttpSession session = req.getSession(false);
        if (req.getSession(false) != null && req.getSession().getAttribute("owner") != null) {
            Owner owner = (Owner) req.getSession().getAttribute("owner");
            if (owner.getCars().contains(car)) {
                req.getRequestDispatcher("/WEB-INF/views/CarCardOwner.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/views/CarCard.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/WEB-INF/views/CarCard.jsp").forward(req, resp);
        }
    }
}
