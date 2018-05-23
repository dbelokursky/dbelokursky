package ru.job4j.musicvenue.dao;

import org.apache.log4j.Logger;
import ru.job4j.musicvenue.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry Belokursky
 * @since 19.05.18.
 */
public class SqlUserDao implements UserDao {

    private static final Logger LOGGER = Logger.getLogger("SqlUserDao.class");

    DaoFactory daoFactory = SqlDaoFactory.INSTANCE;

    @Override
    public void create(User user) {
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "INSERT INTO mv_user(login, password, role_id, address_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRoleId());
            statement.setInt(4, user.getAddressId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(int id, User user) {
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "UPDATE mv_user SET login = ?, password = ?, role_id = ?, address_id = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRoleId());
            statement.setInt(4, user.getAddressId());
            statement.setInt(5, id);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "DELETE FROM mv_user WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public User findById(int id) {
        User user = null;
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "SELECT id, login, password, role_id, address_id FROM mv_user WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRoleId(resultSet.getInt("role_id"));
                user.setAddressId(resultSet.getInt("address_id"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> allUsers = new ArrayList<>();
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "SELECT id, login, password, role_id, address_id FROM mv_user";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRoleId(resultSet.getInt("role_id"));
                user.setAddressId(resultSet.getInt("address_id"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return allUsers;
    }

    public User isExist(String login, String password) {
        User user = null;
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "SELECT id, login, password, role_id, address_id FROM mv_user WHERE login = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRoleId(resultSet.getInt("role_id"));
                user.setAddressId(resultSet.getInt("address_id"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    private User createUser(User user, ResultSet resultSet) throws SQLException {
        user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setRoleId(resultSet.getInt("role_id"));
        user.setAddressId(resultSet.getInt("address_id"));
        return user;
    }
}
