package ru.job4j.carssale;

/**
 * @author Dmitry Belokursky
 * @since 27.06.18.
 */
public class Car {

    private int id;

    private String name;

    private Transmission transmission;

    private Suspension suspension;

    private Engine engine;

    public Car() {
    }

    public Car(int id, String name, Transmission transmission, Suspension suspension, Engine engine) {
        this.id = id;
        this.name = name;
        this.transmission = transmission;
        this.suspension = suspension;
        this.engine = engine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Suspension getSuspension() {
        return suspension;
    }

    public void setSuspension(Suspension suspension) {
        this.suspension = suspension;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}