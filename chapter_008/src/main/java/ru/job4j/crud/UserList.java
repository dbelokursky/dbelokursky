package ru.job4j.crud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Dmitry Belokursky
 * @since 14.04.18.
 */
public class UserList extends HttpServlet {

    UserStore userStore = UserStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.setAttribute("allUsers", printAllUsers());
        session.setAttribute("path", "/it");
        resp.sendRedirect(String.format("%s/list.jsp", req.getContextPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        userStore.removeUser(Integer.parseInt(req.getParameter("userId")));
        HttpSession session = req.getSession();
        session.setAttribute("allUsers", printAllUsers());
        resp.sendRedirect(String.format("%s/list.jsp", req.getContextPath()));
    }

    private String printAllUsers() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> allUsersIds = userStore.getAllUsersIds();
        for (Integer id : allUsersIds) {
            sb.append(printIntoTable(userStore.getUser(id)));
        }
        return sb.toString();
    }

    private String printIntoTable(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("<tr>")
                .append("<form  action= ").append(getServletContext().getContextPath()).append("/list").append(" method='post'>")
                .append("<td><input type='text' name='userId' value=").append(user.getId()).append(" size=3 readonly></td>")
                .append("<td>").append(user.getName()).append("</td>")
                .append("<td>").append(user.getLogin()).append("</td>")
                .append("<td>").append(user.getEmail()).append("</td>")
                .append("<td>").append(user.getCreateDate()).append("</td>")
                .append("<td><input type='submit' value='Edit' formaction = ").append(getServletContext().getContextPath()).append("/edit></td>")
                .append("<td><input type='submit' value='Delete' formaction = ").append(getServletContext().getContextPath()).append("/list></td>");
        return sb.toString();
    }
}
