package ru.job4j.crud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dmitry Belokursky
 * @since 14.04.18.
 */
public class UserCreate extends HttpServlet {

    UserStore userStore = UserStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(String.format("%s/create.jsp", req.getContextPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        userStore.addUser(user);
        resp.sendRedirect(String.format("%s/create.jsp", req.getContextPath()));
    }
}
