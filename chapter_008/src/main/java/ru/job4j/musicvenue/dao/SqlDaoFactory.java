package ru.job4j.musicvenue.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Dmitry Belokursky
 * @since 18.05.18.
 */
public enum SqlDaoFactory implements DaoFactory {

    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger("SqlDaoFactory.class");

    private static BasicDataSource dataSource;

    static {
        org.apache.log4j.PropertyConfigurator.configure("/opt/tomcat/webapps/it/resources/log4j.properties");
        Properties properties = new Properties();
        try (FileInputStream dbProperties = new FileInputStream("/opt/tomcat/webapps/it/resources/db.properties")) {
            properties.load(dbProperties);
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName(properties.getProperty("driverClassName"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public UserDao getUserDao() {
        return new SqlUserDao();
    }

    @Override
    public RoleDao getRoleDao() {
        return new SqlRoleDao();
    }

    @Override
    public MusicTypeDao getMusicTypeDao() {
        return new SqlMusicTypeDao();
    }

    @Override
    public AddressDao getAddressDao() {
        return new SqlAddressDao();
    }
}
