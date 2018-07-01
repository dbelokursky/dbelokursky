package ru.job4j.todo;

import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.function.Function;

/**
 * @author Dmitry Belokursky
 * @since 10.06.18.
 */
@Log4j
public enum ItemsStore {

    INSTANCE;

    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void add(Item item) {
        tx(session -> session.save(item));
    }

    public List<Item> getAll() {
        return tx(session -> session.createQuery("from Item").list());
    }

    private <R> R tx(final Function<Session, R> command) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            return command.apply(session);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
