package ru.job4j.crud;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dmitry Belokursky
 * @since 14.04.18.
 */
public class UserEdit extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger("UserEdit.class");

    private final UserStore userStore = UserStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            req.getRequestDispatcher("/WEB-INF/views/user/list.jsp").forward(req, resp);
        } catch (ServletException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            req.setAttribute("user", userStore.getUser(Integer.parseInt(req.getParameter("userId"))));
            User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
            userStore.editUser(Integer.parseInt(req.getParameter("userId")), user);
            req.getRequestDispatcher("/WEB-INF/views/user/edit.jsp").forward(req, resp);
        } catch (ServletException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
