package ru.job4j.carssale;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Dmitry Belokursky
 * @since 27.06.18.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int carId;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @ManyToOne(cascade = CascadeType.ALL)
    private Transmission transmissionId;

    @ManyToOne(cascade = CascadeType.ALL)
    private Suspension suspension_id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Engine engine_id;
}