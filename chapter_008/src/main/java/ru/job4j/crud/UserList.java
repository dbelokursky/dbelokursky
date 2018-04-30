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
public class UserList extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger("UserList.class");

    private final UserStore userStore = UserStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            req.setAttribute("users", userStore.getAllUsers());
            req.getRequestDispatcher("/WEB-INF/views/user/list.jsp").forward(req, resp);
        } catch (ServletException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        userStore.removeUser(Integer.parseInt(req.getParameter("userId")));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
