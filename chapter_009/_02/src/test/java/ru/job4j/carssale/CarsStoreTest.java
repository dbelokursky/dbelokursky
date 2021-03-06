package ru.job4j.carssale;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.carssale.models.Car;
import ru.job4j.carssale.models.Image;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CarsStoreTest {
    // Prepare the Hibernate configuration
    StandardServiceRegistry reg = new StandardServiceRegistryBuilder().configure().build();
    MetadataSources metaDataSrc = new MetadataSources(reg);
    // Get database connection
    Connection con = metaDataSrc.getServiceRegistry().getService(ConnectionProvider.class).getConnection();
    JdbcConnection jdbcCon = new JdbcConnection(con);
    // Initialize Liquibase and run the update
    Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(jdbcCon);
    Liquibase liquibase = new Liquibase("db.changelog-master.xml", new ClassLoaderResourceAccessor(), database);

    public CarsStoreTest() throws SQLException, DatabaseException {
    }

    @Before
    public void init() throws LiquibaseException {
        liquibase.update("test");
    }

    @Test
    public void add() {
        CarsStore carsStore = new CarsStore();
        Car car = new Car();
        Set<Image> images = new HashSet<>();
        car.setModel("testModel");
        car.setBrand("testBrand");
        car.setImages(images);
        carsStore.add(car, images);
        assertThat(car, is(carsStore.getCar(car.getId())));
    }

    @Test
    public void update() {
        CarsStore carsStore = new CarsStore();
        Car car = new Car();
        car.setId(1);
        car.setModel("modelUpdated");
        car.setBrand("brandUpdated");
        car.setSold(true);
        carsStore.update(car);

        String expected = "modelUpdated";
        String result = carsStore.getCar(1).getModel();
        assertThat(result, is(expected));
    }

    @Test
    public void getAll() {
        CarsStore carsStore = new CarsStore();
        int expected = 7;
        int result = carsStore.getAll().size();
        assertThat(result, is(expected));
    }

    @Test
    public void getCar() {
        CarsStore carsStore = new CarsStore();
        String expected = "model1";
        String result = carsStore.getCar(1).getModel();
        assertThat(result, is(expected));
    }

    @Test
    public void getByBrand() {
        CarsStore carsStore = new CarsStore();
        int expected = 3;
        int result = carsStore.getByBrand("brand5").size();
        assertThat(result, is(expected));
    }

    @Test
    public void getByModel() {
        CarsStore carsStore = new CarsStore();
        int expected = 2;
        int result = carsStore.getByModel("model55").size();
        assertThat(result, is(expected));
    }

    @Test
    public void getUnsold() {
        CarsStore carsStore = new CarsStore();
        int expected = 6;
        int result = carsStore.getUnsold().size();
        assertThat(result, is(expected));
    }

    @Test
    public void getWithImages() {
        CarsStore carsStore = new CarsStore();
        int expected = 4;
        int result = carsStore.getWithImages().size();
        assertThat(result, is(expected));
    }
}