package ru.job4j.carssale;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dmitry Belokursky
 * @since 30.06.18.
 */
public class CarsList extends HttpServlet {

    private final CarsStore carsStore = CarsStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("cars", carsStore.getAll());
//        PrintWriter writer = resp.getWriter();
//        writer.write("IIIIIIIIIIIIIIIIII");
    }
}
