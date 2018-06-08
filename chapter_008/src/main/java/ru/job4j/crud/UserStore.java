package ru.job4j.crud;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import ru.job4j.crud.models.Role;
import ru.job4j.crud.models.User;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

/**
 * @author Dmitry Belokursky
 * @since 08.04.18.
 */
public enum UserStore {

    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger("UserStore.class");

    private static BasicDataSource dataSource;

    static {
        String basePath = new File("").getAbsolutePath();
        org.apache.log4j.PropertyConfigurator.configure(basePath + "/src/main/webapp/resources/log4j.properties");
        Properties properties = new Properties();
        try (FileInputStream dbProperties = new FileInputStream(basePath + "/src/main/webapp/resources/db.properties")) {
            properties.load(dbProperties);
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
            PreparedStatement ps = connection.prepareStatement("INSERT INTO user_store(name, login, email, create_date, password, role, country, city) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.setTimestamp(4, user.getCreateDate());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getRole().getName());
            ps.setString(7, user.getCountry());
            ps.setString(8, user.getCity());
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
            PreparedStatement ps = connection.prepareStatement("UPDATE user_store SET name = ?, login = ?, email = ?, password = ?, role = ?, country = ?, city = ? WHERE id = ?");
            ps.setString(1, newUser.getName());
            ps.setString(2, newUser.getLogin());
            ps.setString(3, newUser.getEmail());
            ps.setString(4, newUser.getPassword());
            ps.setString(5, newUser.getRole().getName());
            ps.setString(6, newUser.getCountry());
            ps.setString(7, newUser.getCity());
            ps.setInt(8, userId);
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
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setCreateDate(rs.getTimestamp("create_date"));
                user.setPassword(rs.getString("password"));
                user.setRole(new Role(rs.getString("role")));
                user.setCountry(rs.getString("country"));
                user.setCity(rs.getString("city"));
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

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id, name, login, email, create_date, password, role, country, city FROM user_store");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setCreateDate(rs.getTimestamp("create_date"));
                user.setPassword(rs.getString("password"));
                user.setRole(new Role(rs.getString("role")));
                user.setCountry(rs.getString("country"));
                user.setCity(rs.getString("city"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return allUsers;
    }

    public User isExists(String login, String password) {
        User user = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT id, name, login, email, password, create_date, role, country, city FROM user_store WHERE login = ? AND password = ?");
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setCreateDate(rs.getTimestamp("create_date"));
                user.setPassword(rs.getString("password"));
                user.setRole(new Role(rs.getString("role")));
                user.setCountry(rs.getString("country"));
                user.setCity(rs.getString("city"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    public Map<Integer, String> getSimilarCountries(String query) {
        Map<Integer, String> result = new HashMap<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT country_id, name FROM country WHERE lower(name) LIKE ?");
            StringBuilder sb = new StringBuilder();
            sb.append("%").append(query.toLowerCase()).append("%");
            ps.setString(1, sb.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.put(rs.getInt("country_id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    public List<String> getSimilarCities(String query) {
        List<String> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT name FROM city WHERE lower(name) LIKE  ?");
            StringBuilder sb = new StringBuilder();
            sb.append("%").append(query.toLowerCase()).append("%");
            ps.setString(1, sb.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }
}