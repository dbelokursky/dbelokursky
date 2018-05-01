package ru.job4j.crud;

import ru.job4j.crud.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Dmitry Belokursky
 * @since 30.04.18.
 */
public class UserLogin extends HttpServlet {

    private final UserStore userStore = UserStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/LoginView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userStore.isExists(login, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            session.setAttribute("role", user.getRole().getName());
            session.setAttribute("id", user.getId());
            session.setAttribute("user", user);
            resp.sendRedirect(String.format("%s/list", req.getContextPath()));
        } else {
            resp.sendRedirect(String.format("%s/login", req.getContextPath()));
        }
    }
}
