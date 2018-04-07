package ru.job4j.parser;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.Date;
import java.util.Properties;

/**
 * @author Dmitry Belokursky
 * @since 02.04.18.
 */
public class DataBase {

    private static final String PATH = "./chapter_007/src/main/java/ru/job4j/parser/resources/";

    private static final Logger LOGGER = Logger.getLogger("DataBase.class");

    private Connection connection;

    protected Connection getConnection() {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(PATH + "parser.properties")) {
            properties.load(fileInputStream);
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return connection;
    }

    protected void executeInitSql() {
        PrintStream stdOut = System.out;
        try (PrintStream ps = new PrintStream(new File(PATH + "init.sql.log"))) {
            System.setOut(ps);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        try (Reader reader = new BufferedReader(new FileReader(PATH + "init.sql"))) {
            scriptRunner.runScript(reader);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            System.setOut(stdOut);
        }
    }

    protected boolean isFirstLaunch() {
        boolean firstLaunch = false;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM joboffers;");
            if (!resultSet.next()) {
                firstLaunch = true;
            }
            return firstLaunch;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return firstLaunch;
    }

    protected void insertRecord(Connection connection, String name, String url, Date date) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO joboffers (name, url, last_update_time) VALUES (?, ?, ?) ON CONFLICT DO NOTHING");
        ps.setString(1, name);
        ps.setString(2, url);
        ps.setTimestamp(3, new Timestamp(date.getTime()));
        ps.executeUpdate();
    }
}
