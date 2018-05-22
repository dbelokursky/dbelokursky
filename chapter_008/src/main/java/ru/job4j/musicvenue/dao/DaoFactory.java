package ru.job4j.musicvenue.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Dmitry Belokursky
 * @since 19.05.18.
 */
public interface DaoFactory {

    /**
     * Returns database connection.
     *
     * @return database connection
     * @throws SQLException
     */
    Connection getConnection() throws SQLException;

    /**
     * Returns an object to control persistent state of the User object.
     *
     * @return an object to control persistent state of the User object
     */
    UserDao getUserDao();

    /**
     * Returns an object to control persistent state of the Role object.
     *
     * @return an object to control persistent state of the Role object
     */
    RoleDao getRoleDao();

    /**
     * Returns an object to control persistent state of the MusicType object.
     *
     * @return an object to control persistent state of the MusicType object
     */
    MusicTypeDao getMusicTypeDao();

    /**
     * Returns an object to control persistent state of the Address object.
     *
     * @return an object to control persistent state of the Address object
     */
    AddressDao getAddressDao();
}
