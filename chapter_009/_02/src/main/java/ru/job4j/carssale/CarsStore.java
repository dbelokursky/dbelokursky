package ru.job4j.carssale;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Dmitry Belokursky
 * @since 27.06.18.
 */
public enum CarsStore {

    INSTANCE;

    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void add(Car car) {
        Engine engine = new Engine();
    }

//    public List<Car> getAll() {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            List<Car> cars = new ArrayList<>();
//            session.save(new Car());
//            cars = session.createQuery("from Car").list();
//        }
//    }

}
