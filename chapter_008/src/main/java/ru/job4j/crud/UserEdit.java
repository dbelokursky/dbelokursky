package ru.job4j.crud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Dmitry Belokursky
 * @since 14.04.18.
 */
public class UserEdit extends HttpServlet {

    UserStore userStore = UserStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(
                "<!DOCTYPE html>"
                        + "<html lang='ru'>"
                        + "    <head>"
                        + "        <meta charset='utf-8'>"
                        + "        <title>Edit user</title>"
                        + "        <a href='/it/list'>Users list</a>"
                        + "        <h1>Edit user</h1>"
                        + "        <table border=1 bordercolor=black cellpadding=5>"
                        + "        <tr>"
                        + "        <th>ID</th>"
                        + "        <th>Name</th>"
                        + "        <th>Login</th>"
                        + "        <th>Email</th>"
                        + "        <th>Edit</th>"
                        + "        </tr>"
                        +          printIntoTable(userStore.getUser(Integer.parseInt(req.getParameter("userId"))))
                        + "        </table>"
                        + "    </head>"
                        + "    <body>"
                        + "    </body>"
                        + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(
                "<!DOCTYPE html>"
                        + "<html lang='ru'>"
                        + "    <head>"
                        + "        <meta charset='utf-8'>"
                        + "        <title>Edit user</title>"
                        + "        <h1>Edit user</h1>"
                        + "        <a href='/it/list'>Users list</a>"
                        + "        <table border=1 bordercolor=black cellpadding=5>"
                        + "        <tr>"
                        + "        <th>ID</th>"
                        + "        <th>Name</th>"
                        + "        <th>Login</th>"
                        + "        <th>Email</th>"
                        + "        <th>Edit</th>"
                        + "        </tr>"
                        + printIntoTable(userStore.getUser(Integer.parseInt(req.getParameter("userId"))))
                        + "        </table>"
                        + "    </head>"
                        + "    <body>"
                        + "    </body>"
                        + "</html>");
        writer.flush();
        User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        userStore.editUser(Integer.parseInt(req.getParameter("userId")), user);
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
