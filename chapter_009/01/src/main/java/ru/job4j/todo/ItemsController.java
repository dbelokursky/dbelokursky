package ru.job4j.todo;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * @author Dmitry Belokursky
 * @since 17.06.18.
 */
public class ItemsController extends HttpServlet {

    private ItemsStore itemsStore = ItemsStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(new Gson().toJson(itemsStore.getAll()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Item item = new Item();
        item.setDescription(req.getParameter("description"));
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        itemsStore.add(item);
    }
}
