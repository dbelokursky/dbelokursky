package ru.job4j.crud;

import org.apache.log4j.Logger;
import ru.job4j.crud.models.Role;
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
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        try {
            if (userRole.equals("ADMIN")) {
                int userId = Integer.parseInt(req.getParameter("userId"));
                req.setAttribute("user", userStore.getUser(userId));
                User user = new User();
                user.setName(req.getParameter("name"));
                user.setLogin(req.getParameter("login"));
                user.setEmail(req.getParameter("email"));
                user.setPassword(req.getParameter("password"));
                user.setRole(new Role(req.getParameter("role")));
                user.setCountry(req.getParameter("country"));
                user.setCity(req.getParameter("city"));
                userStore.editUser(userId, user);
                req.getRequestDispatcher("/WEB-INF/views/admin/Edit.jsp").forward(req, resp);
            } else if (userRole.equals("USER")) {
                int userId = Integer.parseInt(String.valueOf(req.getSession().getAttribute("id")));
                req.setAttribute("user", userStore.getUser(userId));
                User user = new User();
                user.setName(req.getParameter("name"));
                user.setLogin(req.getParameter("login"));
                user.setEmail(req.getParameter("email"));
                user.setPassword(req.getParameter("password"));
                user.setRole(new Role(req.getParameter("role")));
                user.setCountry(req.getParameter("country"));
                user.setCity(req.getParameter("city"));
                userStore.editUser(userId, user);
                req.getRequestDispatcher("/WEB-INF/views/user/EditUserView.jsp").forward(req, resp);
            }
        } catch (ServletException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
