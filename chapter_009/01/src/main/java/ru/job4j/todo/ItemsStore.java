package ru.job4j.todo;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
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
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void update(int id, Item newItem) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            newItem.setId(id);
            session.update(newItem);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            items = session.createQuery("from Item").list();
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return items;
    }
}
