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
public class UserCreate extends HttpServlet {

    UserStore userStore = UserStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "      <html lang=ru>"
                + "      <head>"
                + "        <meta charset=utf-8/>"
                + "        <title>Create user</title>"
                + "        <h1>Create user</h1>"
                + "        <a href='/it/list'>Users list</a>"
                + "        <table border=1 bordercolor=black cellpadding=5>"
                + "        <tr><th>Name</th><th>Login</th><th>Email</th><th>Add</th></tr>"
                + "        <tr>"
                + "        <form  action='/it/create' method='post'>"
                + "        <td><input type='text' size='20' name='name'></td>"
                + "        <td><input type='text' size='20' name='login'></td>"
                + "        <td><input type='text' size='20' name='email'></td>"
                + "        <td><input type='submit'></td>"
                + "        </form>"
                + "        </tr>"
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
        writer.append("<!DOCTYPE html>"
                + "<html lang=ru>"
                + "    <head>"
                + "        <meta charset=utf-8/>"
                + "        <title>Create user</title>"
                + "        <h1>Create user</h1>"
                + "        <a href='/it/list'>Users list</a>"
                + "        <table border=1 bordercolor=black cellpadding=5>"
                + "        <tr><th>Name</th><th>Login</th><th>Email</th><th>Add</th></tr>"
                + "        <tr>"
                + "        <form  action='/it/create' method='post'>"
                + "        <td><input type='text' size='20' name='name'></td>"
                + "        <td><input type='text' size='20' name='login'></td>"
                + "        <td><input type='text' size='20' name='email'></td>"
                + "        <td><input type='submit'></td>"
                + "        </form>"
                + "        </tr>"
                + "        </table>"
                + "    </head>"
                + "    <body>"
                + "    </body>"
                + "</html>");
        writer.flush();

        User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        if (userStore.addUser(user)) {
            writer.append("The user was successfully created.");
            writer.flush();
        }


    }
}
