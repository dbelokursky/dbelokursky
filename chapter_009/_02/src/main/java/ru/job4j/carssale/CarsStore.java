package ru.job4j.carssale;

import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.carssale.models.Car;
import ru.job4j.carssale.models.Image;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Dmitry Belokursky
 * @since 27.06.18.
 */
@Log4j
public class CarsStore {

    private final SessionFactory sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();

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

    public void update(Car car) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Car newCar = session.load(Car.class, car.getId());
            newCar.setBrand(car.getBrand());
            newCar.setModel(car.getModel());
            newCar.setTransmission(car.getTransmission());
            newCar.setSuspension(car.getSuspension());
            newCar.setEngine(car.getEngine());
            newCar.setSold(car.isSold());
            session.update(newCar);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                log.error(e.getMessage(), e);
            }
        }
    }

    public List<Car> getAll() {
        return tx(session -> session.createQuery("from Car").list());
    }

    public Car getCar(int id) {
        return tx(session -> session.get(Car.class, id));
    }

    private <R> R tx(final Function<Session, R> command) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            return command.apply(session);
        } catch (final Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}