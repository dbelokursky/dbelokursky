package ru.job4j.carssale;

import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.carssale.models.Owner;

import java.util.List;

@Log4j
public class OwnerStore {

    private final SessionFactory sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();

    public Owner isExist(String login, String password) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<Owner> query = session.createQuery("from Owner where login = (?1) and password = (?2)");
            query.setParameter(1, login);
            query.setParameter(2, password);
            List<Owner> owners = query.getResultList();
            transaction.commit();
            return owners.size() > 0 ? owners.get(0) : null;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
