package ru.job4j.crud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author Dmitry Belokursky
 * @since 14.04.18.
 */
public class UserList extends HttpServlet {

    UserStore userStore = UserStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(
                "<!DOCTYPE html>" +
                        "<html lang='ru'>" +
                        "    <head>" +
                        "        <meta charset='utf-8'>" +
                        "        <title>Users list</title>" +
                        "        <h1>Users list</h1>" +
                        "        <table border=1 bordercolor=black cellpadding=5>" +
                        "        <tr>" +
                        "        <th>ID</th>" +
                        "        <th>Name</th>" +
                        "        <th>Login</th>" +
                        "        <th>Email</th>" +
                        "        <th>Create date</th>" +
                        "        <th>Edit</th>" +
                        "        <th>Delete</th>" +
                        "        </tr>" +
                        printAllUsers() +
                        "        </table>" +
                        "    </head>" +
                        "    <body>" +
                        "    <a href='/it/create'>Create new user</a>" +
                        "    </body>" +
                        "</html>");
        writer.flush();
    }

    private String printAllUsers() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> allUsersIds = userStore.getAllUsersIds();
        for (Integer id : allUsersIds) {
            sb.append(printIntoTable(userStore.getUser(id)));
        }
        return sb.toString();
    }

    public String printIntoTable(User user) {
        return "<tr>" +
                "<form  action='/it/list' method='post'>" +
                "<td><input type='text' name='userId' value=" + user.getId() + " size=3 readonly>" + "</td>" +
                "<td>" + user.getName() + "</td>" +
                "<td>" + user.getLogin() + "</td>" +
                "<td>" + user.getEmail() + "</td>" +
                "<td>" + user.getCreateDate() + "</td>" +
                "<td><input type='submit' value='Edit' formaction = '/it/edit'></td>" +
                "<td><input type='submit' value='Delete' formaction = '/it/list'></td>" +
                "</form>" +
                "</tr>";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        userStore.removeUser(Integer.parseInt(req.getParameter("userId")));
        writer.append(
                "<!DOCTYPE html>" +
                        "<html lang='ru'>" +
                        "    <head>" +
                        "        <meta charset='utf-8'>" +
                        "        <title>Users list</title>" +
                        "        <h1>Users list</h1>" +
                        "        <table border=1 bordercolor=black cellpadding=5>" +
                        "        <tr>" +
                        "        <th>ID</th>" +
                        "        <th>Name</th>" +
                        "        <th>Login</th>" +
                        "        <th>Email</th>" +
                        "        <th>Create date</th>" +
                        "        <th>Edit</th>" +
                        "        <th>Delete</th>" +
                        "        </tr>" +
                        printAllUsers() +
                        "        </table>" +
                        "    </head>" +
                        "    <body>" +
                        "    <a href='/it/create'>Create new user</a>" +
                        "    </body>" +
                        "</html>");
        writer.flush();
    }
}
