package ru.job4j.crud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Dmitry Belokursky
 * @since 14.04.18.
 */
public class UserEdit extends HttpServlet {

    UserStore userStore = UserStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(String.format("%s/list.jsp", req.getContextPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.setAttribute("user", userStore.getUser(Integer.parseInt(req.getParameter("userId"))));
        User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        userStore.editUser(Integer.parseInt(req.getParameter("userId")), user);
        resp.sendRedirect(String.format("%s/edit.jsp", req.getContextPath()));
    }
}
