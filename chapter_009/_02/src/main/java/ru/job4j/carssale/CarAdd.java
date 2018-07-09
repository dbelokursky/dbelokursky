package ru.job4j.carssale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CarAdd extends HttpServlet {

    private final CarsStore carsStore = CarsStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/CarAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String model = req.getParameter("model");
        String brand = req.getParameter("brand");
        Boolean soldStatus = Boolean.parseBoolean(req.getParameter("sold"));

        Transmission transmission = new Transmission();
        transmission.setName(req.getParameter("transmission"));

        Suspension suspension = new Suspension();
        suspension.setName(req.getParameter("suspension"));

        Engine engine = new Engine();
        engine.setName(req.getParameter("engine"));

        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setSuspension(suspension);
        car.setTransmission(transmission);
        car.setEngine(engine);
        car.setSold(soldStatus);

        carsStore.add(car);
        resp.sendRedirect(String.format("%s/cars", req.getContextPath()));
    }
}
