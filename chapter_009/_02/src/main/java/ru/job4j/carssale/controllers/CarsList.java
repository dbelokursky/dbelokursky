package ru.job4j.carssale.controllers;

import ru.job4j.carssale.CarsStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dmitry Belokursky
 * @since 30.06.18.
 */
public class CarsList extends HttpServlet {

    private final CarsStore carsStore = new CarsStore();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cars", carsStore.getAll());
        req.getRequestDispatcher("/WEB-INF/views/CarsList.jsp").forward(req, resp);
    }
}
