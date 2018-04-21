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
        session.setAttribute("user", printIntoTable(userStore.getUser(Integer.parseInt(req.getParameter("userId")))));
        User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        userStore.editUser(Integer.parseInt(req.getParameter("userId")), user);
        resp.sendRedirect(String.format("%s/edit.jsp", req.getContextPath()));
    }

    public String printIntoTable(User user) {
        return "<tr>"
                + "<form  action='/it/list' method='post'>"
                + "<td><input type='text' name='userId' value=" + user.getId() + " size=3 readonly>" + "</td>"
                + "<td><input type='text' size='10' name='name' value=" + user.getName() + "></td>"
                + "<td><input type='text' size='10' name='login' value=" + user.getLogin() + "></td>"
                + "<td><input type='text' size='10' name='email' value=" + user.getEmail() + "></td>"
                + "<td><input type='submit' value='Save' formaction = '/it/edit'></td>"
                + "</form>"
                + "</tr>";
    }
}
