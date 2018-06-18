package ru.job4j.todo;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry Belokursky
 * @since 10.06.18.
 */
public enum ItemsStore {

    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger("ItemsStore.class");

    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void add(Item item) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }

    public void update(int id, Item newItem) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        newItem.setId(id);
        session.update(newItem);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(id);
        session.getTransaction().commit();
        session.close();
    }

    public List<Item> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Item> items = new ArrayList<>();
        items = session.createQuery("from Item").list();
        session.close();
        return items;
    }
}
