package ru.job4j.musicvenue.dao;

import org.apache.log4j.Logger;
import ru.job4j.musicvenue.models.Role;

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
public class SqlRoleDao implements RoleDao {

    private static final Logger LOGGER = Logger.getLogger("SqlRoleDao.class");

    DaoFactory daoFactory = SqlDaoFactory.INSTANCE;

    @Override
    public void create(Role role) {
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "INSERT INTO role(name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, role.getName());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(int id, Role role) {
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "UPDATE role SET name = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, role.getName());
            statement.setInt(2, id);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = daoFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM role WHERE id = ?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public Role findById(int id) {
        Role role = new Role();
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "SELECT (id, name) FROM role WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                role.setId(resultSet.getInt("id"));
                role.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return role;
    }

    @Override
    public List<Role> findAll() {
        List<Role> allRoles = new ArrayList<>();
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "SELECT (id, name) FROM role";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setName(resultSet.getString("name"));
                allRoles.add(role);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return allRoles;
    }
}
