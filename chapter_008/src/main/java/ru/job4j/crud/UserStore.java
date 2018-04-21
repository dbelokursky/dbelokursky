package ru.job4j.crud;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author Dmitry Belokursky
 * @since 08.04.18.
 */
public enum UserStore {

    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger("UserStore.class");

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

    public boolean addUser(User user) {
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

    public boolean editUser(int userId, User newUser) {
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

    public User getUser(int userId) {
        User user = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user_store WHERE id = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("email"),
                        rs.getTimestamp("create_date"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    public boolean removeUser(int userId) {
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

    public ArrayList<Integer> getAllUsersIds() {
        ArrayList<Integer> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id FROM user_store");
            while (rs.next()) {
                result.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }
}