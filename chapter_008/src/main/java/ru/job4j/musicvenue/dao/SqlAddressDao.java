package ru.job4j.musicvenue.dao;

import org.apache.log4j.Logger;
import ru.job4j.musicvenue.models.Address;

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
public class SqlAddressDao implements AddressDao {

    private static final Logger LOGGER = Logger.getLogger("SqlAddressDao.class");

    SqlDaoFactory daoFactory = SqlDaoFactory.INSTANCE;

    @Override
    public void create(Address address) {
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "INSERT INTO address (country, city, street, unit, zip) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getStreet());
            statement.setString(4, address.getUnit());
            statement.setInt(5, address.getZip());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(int id, Address address) {
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "UPDATE address SET country = ?, city = ?, street = ?, unit = ?, zip = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getStreet());
            statement.setString(4, address.getUnit());
            statement.setInt(5, address.getZip());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "DELETE FROM address WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Override
    public Address findById(int id) {
        Address address = new Address();
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "SELECT id, country, city, street, unit FROM address WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                address.setId(resultSet.getInt("id"));
                address.setCountry(resultSet.getString("country"));
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setUnit(resultSet.getString("unit"));
                address.setZip(resultSet.getInt("zip"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return address;
    }

    @Override
    public List<Address> findAll() {
        List<Address> allAddresses = new ArrayList<>();
        try (Connection connection = daoFactory.getConnection()) {
            String sql = "SELECT id, country, city, street, unit, zip FROM address";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Address address = new Address();
                address.setId(resultSet.getInt("id"));
                address.setCountry(resultSet.getString("country"));
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setUnit(resultSet.getString("unit"));
                address.setZip(resultSet.getInt("zip"));
                allAddresses.add(address);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return allAddresses;
    }
}
