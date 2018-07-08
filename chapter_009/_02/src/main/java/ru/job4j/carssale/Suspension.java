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
@Table(name = "suspension")
public class Suspension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "suspension_id")
    private int suspensionId;

    @Column(name = "name")
    private String name;
}
