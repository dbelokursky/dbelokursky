package ru.job4j.carssale.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.graph.GraphAdapterBuilder;
import ru.job4j.carssale.CarsStore;
import ru.job4j.carssale.models.Car;
import ru.job4j.carssale.models.Image;
import ru.job4j.carssale.models.Owner;

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
        GsonBuilder gsonBuilder = new GsonBuilder();
        new GraphAdapterBuilder().addType(Image.class).addType(Owner.class).registerOn(gsonBuilder);
        Gson gson = gsonBuilder.create();

        resp.setContentType("application/json");
        resp.setContentType("UTF-8");
        resp.getWriter().write(gson.toJson(new CarsStore().getAll()));
    }

    private Set<String> getBrands(List<Car> cars) {
        Set<String> brands = new HashSet<>();
        for (Car car : cars) {
            brands.add(car.getBrand());
        }
        return brands;
    }
}
