package ru.job4j.storage.dao;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.storage.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class UserDaoImplTest {
    ClassPathXmlApplicationContext context;
    UserDao userDao;

    @Before
    public void before() throws LiquibaseException, SQLException {
        context = new ClassPathXmlApplicationContext("file:src/test/resources/spring-conf-tests.xml");
        userDao = context.getBean("userDAO", UserDao.class);
        DataSource dataSource = (DataSource) context.getBean("dataSource");
        // Get database connection
        Connection con = dataSource.getConnection();
        JdbcConnection jdbcCon = new JdbcConnection(con);
        // Initialize Liquibase and run the update
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(jdbcCon);
        Liquibase liquibase = new Liquibase("db.changelog-master.xml", new ClassLoaderResourceAccessor(), database);
        liquibase.update("test");
    }

    @Test
    public void add() {
        User expected = new User();
        expected.setId(6);
        expected.setName("name");
        expected.setPassword("password");
        userDao.add(expected);
        User result = userDao.getById(6).get();
        assertThat(result, is(expected));
    }

    @Test
    public void update() {
        User expected = new User();
        expected.setId(1);
        expected.setName("name11");
        expected.setPassword("password11");
        userDao.update(expected);
        User result = userDao.getById(1).get();
        assertThat(result, is(expected));
    }

    @Test
    public void getById() {
        User expected = new User();
        expected.setId(2);
        expected.setName("user2");
        expected.setPassword("password2");
        User result = userDao.getById(2).get();
        assertThat(result, is(expected));
    }

    @Test
    public void deleteById() {
        userDao.deleteById(1);
        assertFalse(userDao.getById(1).isPresent());
    }

    @Test
    public void getAll() {
        int result = userDao.getAll().size();
        int expected = 5;
        assertThat(result, is(expected));
    }
}