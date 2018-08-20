package ru.job4j.carssale.dao;

import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.carssale.HibernateUtil;
import ru.job4j.carssale.models.Car;
import ru.job4j.carssale.models.Image;
import ru.job4j.carssale.models.Owner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Dmitry Belokursky
 * @since 27.06.18.
 */
@Log4j
public class SqlCarDao implements CarDao {

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
            Car updatedCar = session.load(Car.class, car.getId());
            Owner owner = session.load(Owner.class, car.getOwner().getId());
            updatedCar.setId(car.getId());
            updatedCar.setBrand(car.getBrand());
            updatedCar.setModel(car.getModel());
            updatedCar.setTransmission(car.getTransmission());
            updatedCar.setSuspension(car.getSuspension());
            updatedCar.setEngine(car.getEngine());
            updatedCar.setImages(car.getImages());
            updatedCar.setOwner(owner);
            updatedCar.setSold(car.isSold());
            session.update(updatedCar);
            return updatedCar;
        });
    }

    public List<Car> getByBrand(String brand) {
        return tx(session -> {
            Query query = session.createQuery("from Car where brand=(?1)");
            return query.setParameter(1, brand).list();
        });
    }

    public List<Car> getByModel(String model) {
        return tx(session -> {
            Query query = session.createQuery("from Car where model=(?1)");
            return query.setParameter(1, model).list();
        });
    }

    public List<Car> getUnsold() {
        return tx(session -> {
            Query query = session.createQuery("from Car where sold=(?1)");
            return query.setParameter(1, false).list();
        });
    }

    public List<Car> getWithImages() {
        return tx(session -> session.createQuery("from Car where images.size > 0").list());
    }

    public List<Car> getAll() {
        return tx(session -> session.createQuery("from Car").list());
    }

    public Car getCar(int id) {
        return tx(session -> session.get(Car.class, id));
    }

    private <R> R tx(final Function<Session, R> command) {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            transaction.commit();
            session.close();
        }
    }
}