package ru.job4j.crud;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Dmitry Belokursky
 * @since 08.04.18.
 */
public class UserStore {

    private static final Logger LOGGER = Logger.getLogger("UserStore.class");

    private static final UserStore USER_STORE = new UserStore();

    private static BasicDataSource dataSource;

    static {
        org.apache.log4j.PropertyConfigurator.configure("/opt/tomcat/webapps/it/WEB-INF/resources/log4j.properties");
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream("/opt/tomcat/webapps/it/WEB-INF/resources/db.properties")) {
            properties.load(inputStream);
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName(properties.getProperty("driverClassName"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
            dataSource.setMinIdle(Integer.parseInt(properties.getProperty("minIdle")));
            dataSource.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private UserStore() {
    }

    public static UserStore getInstance() {
        return USER_STORE;
    }

    protected BasicDataSource getDataSource(HttpServlet servlet) {
        return dataSource;
    }

    protected boolean addUser(User user) {
        boolean result = false;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO user_store(name, login, email, create_date) VALUES (?, ?, ?, ?)");
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

    protected boolean editUser(int userId, User newUser) {
        boolean result = false;
        try (Connection connection = dataSource.getConnection()) {
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

    protected String getUser(int userId) {
        StringBuilder sb = new StringBuilder();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user_store WHERE id = ?");
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

    protected boolean removeUser(int userId) {
        boolean result = false;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM user_store WHERE id = ?");
            ps.setInt(1, userId);
            result = ps.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }
}