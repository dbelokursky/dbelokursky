package ru.job4j.carssale;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Dmitry Belokursky
 * @since 27.06.18.
 */
public enum CarStore {

    INSTANCE;

    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void add(Car car) {
        Engine engine = new Engine();
    }

}
