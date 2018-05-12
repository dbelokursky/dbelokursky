package ru.job4j.crud;

import org.apache.log4j.Logger;
import ru.job4j.crud.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dmitry Belokursky
 * @since 14.04.18.
 */
public class UserCreate extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger("UserCreate.class");

    private final UserStore userStore = UserStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            req.getRequestDispatcher("/WEB-INF/views/admin/CreateAdminView.jsp").forward(req, resp);
        } catch (ServletException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        User user = new User(
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"),
                req.getParameter("password"),
                req.getParameter("country"),
                req.getParameter("city"));
        userStore.addUser(user);
        try {
            req.getRequestDispatcher("/WEB-INF/views/admin/CreateAdminView.jsp").forward(req, resp);
        } catch (ServletException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
