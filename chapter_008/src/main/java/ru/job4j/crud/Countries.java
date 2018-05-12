package ru.job4j.crud;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Dmitry Belokursky
 * @since 10.05.18.
 */
public class Countries extends HttpServlet {

    private final UserStore store = UserStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String term = req.getParameter("term");
        Map<Integer, String> countries = store.getSimilarCountries(term);
        String json = new Gson().toJson(countries.values());

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

}
