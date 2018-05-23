package ru.job4j.musicvenue.controllers;

import ru.job4j.musicvenue.dao.SqlUserDao;
import ru.job4j.musicvenue.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dmitry Belokursky
 * @since 23.05.18.
 */
public class UsersList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlUserDao userDao = new SqlUserDao();
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("allUsers", userDao.findAll());
        if (user.getId() == 1) {
            req.getRequestDispatcher("/WEB-INF/mv_views/user/UsersList.jsp").forward(req, resp);
        } else if (user.getId() == 2) {
            req.getRequestDispatcher("/WEB-INF/mv_views/moderator/UsersList.jsp").forward(req, resp);
        } else if (user.getId() == 3) {
            req.getRequestDispatcher("/WEB-INF/mv_views/admin/UsersList.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher(String.format("%s/login", req.getContextPath())).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
