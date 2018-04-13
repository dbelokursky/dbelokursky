package ru.job4j.crud;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author Dmitry Belokursky
 * @since 08.04.18.
 */
public class UserStore {

    private static final Logger LOGGER = Logger.getLogger("UserStore.class");

    private static final UserStore USER_STORE = new UserStore();

    private UserStore() {
    }

    public static UserStore getInstance() {
        return USER_STORE;
    }

    protected Connection getConnection(HttpServlet servlet) {
        org.apache.log4j.PropertyConfigurator.configure(servlet.getServletContext().getRealPath("/WEB-INF/resources/log4j.properties"));
        Properties properties = new Properties();
        Connection connection = null;
        try (FileInputStream fileInputStream = new FileInputStream(servlet.getServletContext().getRealPath("/WEB-INF/resources/db.properties"))) {
            properties.load(fileInputStream);
            Class.forName(properties.getProperty("driverClassName"));
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return connection;
    }

    protected boolean addUser(User user, HttpServlet servlet) {
        boolean result = false;
        try (Connection connection = getConnection(servlet)) {
            String addUserQuery = "INSERT INTO user_store(name, login, email, create_date) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(addUserQuery);
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.setTimestamp(4, user.getCreateDate());
            ps.execute();
            result = true;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    protected boolean editUser(int userId, User newUser, HttpServlet servlet) {
        boolean result = false;
        try (Connection connection = getConnection(servlet)) {
            PreparedStatement ps = connection.prepareStatement("UPDATE user_store SET name = ?, login = ?, email = ? WHERE id = ?");
            ps.setString(1, newUser.getName());
            ps.setString(2, newUser.getLogin());
            ps.setString(3, newUser.getEmail());
            ps.setInt(4, userId);
            result = ps.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    protected String getUser(int userId, HttpServlet servlet) {
        StringBuilder sb = new StringBuilder();
        try (Connection connection = getConnection(servlet)) {
            PreparedStatement ps = connection.prepareStatement("SELECT (name, login, email) FROM user_store WHERE id = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sb.append(rs.getInt("id"))
                        .append(" ")
                        .append(rs.getString("name"))
                        .append(" ")
                        .append(rs.getString("login"))
                        .append(" ")
                        .append(rs.getString("email"))
                        .append(" ")
                        .append(rs.getTimestamp("create_date"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return sb.toString();
    }

    protected boolean removeUser(int userId, HttpServlet servlet) {
        boolean result = false;
        try (Connection connection = getConnection(servlet)) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM user_store WHERE id = ?");
            ps.setInt(1, userId);
            result = ps.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }
}
