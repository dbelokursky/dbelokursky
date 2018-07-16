package ru.job4j.carssale;

import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.carssale.models.Car;
import ru.job4j.carssale.models.Image;

import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Dmitry Belokursky
 * @since 27.06.18.
 */
@Log4j
public enum CarsStore {

    INSTANCE;

    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void add(Car car, Set<Image> images) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(car);
            Set<Image> tmpImages = new HashSet<>();
            for (Image img : images) {
                img.setCar(car);
                tmpImages.add(img);
            }
            car.setImages(tmpImages);
            session.save(car);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error(e.getMessage(), e);
        }
    }

    public List<Car> getAll() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List<Car> cars = session.createQuery("from Car").list();
            transaction.commit();
            return cars;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    public Car getCar(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Car where id = (?1)");
            query.setParameter(1, id);
            List<Car> cars = query.getResultList();
            transaction.commit();
            return cars.get(0);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
