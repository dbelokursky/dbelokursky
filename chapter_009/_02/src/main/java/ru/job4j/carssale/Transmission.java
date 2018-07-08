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
@Table(name = "transmission")
public class Transmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transmission_id")
    private int transmissionId;

    @Column(name = "name")
    private String name;
}
