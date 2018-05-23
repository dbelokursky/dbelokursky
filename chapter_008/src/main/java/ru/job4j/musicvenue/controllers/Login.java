package ru.job4j.musicvenue.controllers;

import ru.job4j.musicvenue.dao.SqlUserDao;
import ru.job4j.musicvenue.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Dmitry Belokursky
 * @since 22.05.18.
 */
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/mv_views/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlUserDao userDao = new SqlUserDao();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userDao.isExist(login, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect(String.format("%s/list", req.getContextPath()));
        } else {
            resp.sendRedirect(String.format("%s/login", req.getContextPath()));
        }
    }
}
