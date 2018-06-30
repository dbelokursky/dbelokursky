package ru.job4j.todo;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

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
        addTransaction((session -> session.save(item)), item);
    }

    public List<Item> getAll() {
        return getAllTransaction(session -> session.createQuery("from Item").list());
    }

    public void update(int id, Item newItem) {
        updateTransaction(session -> session.update(newItem), id, newItem);
    }

    public void delete(int id) {
        deleteTransaction(session -> session.delete(id), id);

    }

    private void addTransaction(Consumer<Session> consumer, Item item) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error(e.getMessage(), e);
        }
    }

    private <R> R getAllTransaction(final Function<Session, R> command) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            return command.apply(session);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

    private void updateTransaction(Consumer<Session> consumer, int id, Item newItem) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            newItem.setId(id);
            session.update(newItem);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void deleteTransaction(Consumer<Session> consumer, int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error(e.getMessage(), e);
        }
    }
}
