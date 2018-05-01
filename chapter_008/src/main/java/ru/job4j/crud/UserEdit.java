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
public class UserEdit extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger("UserEdit.class");

    private final UserStore userStore = UserStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            req.getRequestDispatcher("/WEB-INF/views/user/ListUserView.jsp").forward(req, resp);
        } catch (ServletException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userRole = (String) req.getSession().getAttribute("role");
        try {
            if (userRole.equals("ADMIN")) {
                int userId = Integer.parseInt(req.getParameter("userId"));
                req.setAttribute("user", userStore.getUser(userId));
                User user = new User(
                        req.getParameter("name"),
                        req.getParameter("login"),
                        req.getParameter("email"),
                        req.getParameter("password"),
                        req.getParameter("role"));
                userStore.editUser(userId, user);
                req.getRequestDispatcher("/WEB-INF/views/admin/EditAdminView.jsp").forward(req, resp);
            } else if (userRole.equals("USER")) {
                int userId = Integer.parseInt(String.valueOf(req.getSession().getAttribute("id")));
                req.setAttribute("user", userStore.getUser(userId));
                User user = new User(
                        req.getParameter("name"),
                        req.getParameter("login"),
                        req.getParameter("email"),
                        req.getParameter("password"));
                userStore.editUser(userId, user);
                req.getRequestDispatcher("/WEB-INF/views/user/EditUserView.jsp").forward(req, resp);
            }
        } catch (ServletException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
