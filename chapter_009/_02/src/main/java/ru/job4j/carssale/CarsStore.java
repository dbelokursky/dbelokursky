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

    public Car add(Car car, Set<Image> images) {
        return tx(session -> {
            Set<Image> carImages = new HashSet<>();
            for (Image img : images) {
                img.setCar(car);
                carImages.add(img);
            }
            car.setImages(carImages);
            session.save(car);
            return car;
        });
    }

    public Car update(Car car) {
        return tx(session -> {
            Car updatedCar = session.get(Car.class, car.getId());
            updatedCar.setBrand(car.getBrand());
            updatedCar.setModel(car.getModel());
            updatedCar.setTransmission(car.getTransmission());
            updatedCar.setSuspension(car.getSuspension());
            updatedCar.setEngine(car.getEngine());
            updatedCar.setSold(car.isSold());
            session.update(updatedCar);
            return updatedCar;
        });
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