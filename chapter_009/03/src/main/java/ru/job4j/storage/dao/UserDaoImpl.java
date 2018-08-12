package ru.job4j.storage.dao;

import lombok.extern.log4j.Log4j;
import ru.job4j.storage.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j
public class UserDaoImpl implements UserDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user) {
        String add = "INSERT INTO storage_user (name, password) VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        String update = "UPDATE storage_user SET name=?, password=? WHERE user_id=?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> getById(int userId) {
        String get = "SELECT * FROM storage_user WHERE user_id=?";
        User user = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(get);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void deleteById(int userId) {
        String delete = "DELETE FROM storage_user WHERE user_id=?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String getAll = "SELECT * FROM storage_user";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }
}
