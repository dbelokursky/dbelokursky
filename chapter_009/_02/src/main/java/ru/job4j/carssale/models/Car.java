package ru.job4j.carssale.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Dmitry Belokursky
 * @since 27.06.18.
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = "images")
@NoArgsConstructor
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "suspension_id")
    private Suspension suspension;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @Column(name = "sold")
    private boolean sold;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "car")
    private Set<Image> images = new HashSet<>();
}