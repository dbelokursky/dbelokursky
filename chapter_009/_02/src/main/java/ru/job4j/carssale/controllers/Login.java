package ru.job4j.carssale.controllers;

import ru.job4j.carssale.OwnerStore;
import ru.job4j.carssale.models.Owner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Login extends HttpServlet {

    private final OwnerStore ownerStore = new OwnerStore();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Owner owner = ownerStore.isExist(login, password);
        if (owner != null) {
            HttpSession session = req.getSession();
            session.setAttribute("owner", owner);
            resp.sendRedirect(String.format("%s/cars", req.getContextPath()));
        } else {
            resp.sendRedirect(String.format("%s/login", req.getContextPath()));
        }

    }
}
