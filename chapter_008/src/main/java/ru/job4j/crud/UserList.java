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
            if (req.getSession().getAttribute("role").equals("USER")) {
                req.getRequestDispatcher("/WEB-INF/views/user/ListUserView.jsp").forward(req, resp);
            } else if (req.getSession().getAttribute("role").equals("ADMIN")) {
                req.getRequestDispatcher("/WEB-INF/views/admin/ListAdminView.jsp").forward(req, resp);
            }
        } catch (ServletException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        userStore.removeUser(Integer.parseInt(req.getParameter("userId")));
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
