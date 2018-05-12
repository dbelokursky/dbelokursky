package ru.job4j.crud;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Dmitry Belokursky
 * @since 12.05.18.
 */
public class Cities extends HttpServlet {

    private final UserStore store = UserStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String term = req.getParameter("term");
        List<String> cities = store.getSimilarCities(term);
        String json = new Gson().toJson(cities);

        resp.setContentType("application/json");  // Set content type of the response so that jQuery knows what it can expect.
        resp.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        resp.getWriter().write(json);       // Write response body.
    }
}
