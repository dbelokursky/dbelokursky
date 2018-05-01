package ru.job4j.crud;

import org.apache.log4j.Logger;
import ru.job4j.crud.models.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Dmitry Belokursky
 * @since 08.04.18.
 */
public class UsersServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger("UsersServlet.class");

    private final UserStore users = UserStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html");
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            int userId = Integer.parseInt(req.getParameter("userId"));
            writer.append(users.getUser(userId).toString());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        int userId = Integer.parseInt(req.getParameter("userId"));
        User user = new User(name, login, email, password);
        users.editUser(userId, user);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = new User(name, login, email, password);
        users.addUser(user);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        int userId = Integer.parseInt(req.getParameter("userId"));
        users.removeUser(userId);
    }
}
