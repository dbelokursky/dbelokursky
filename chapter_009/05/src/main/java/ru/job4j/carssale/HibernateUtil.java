package ru.job4j.carssale;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public enum HibernateUtil {

    INSTANCE;

    private final static SessionFactory SESSION_FACTORY;

    static {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
