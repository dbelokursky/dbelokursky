package ru.job4j.carssale.controllers;

import com.google.gson.Gson;
import ru.job4j.carssale.CarsStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Cars extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CarsStore carsStore = new CarsStore();
        resp.setContentType("application/json");
        resp.setContentType("UTF-8");
        resp.getWriter().write(new Gson().toJson(carsStore.getAll()));
    }
}
