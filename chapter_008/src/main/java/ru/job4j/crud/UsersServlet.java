package ru.job4j.crud;

import org.apache.log4j.Logger;

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

    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html");
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            int userId = Integer.parseInt(req.getParameter("userId"));
            writer.append(users.getUser(userId, this));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        int userId = Integer.parseInt(req.getParameter("userId"));
        User user = new User(name, login, email);
        users.editUser(userId, user, this);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        User user = new User(name, login, email);
        users.addUser(user, this);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        int userId = Integer.parseInt(req.getParameter("userId"));
        users.removeUser(userId, this);
    }
}
