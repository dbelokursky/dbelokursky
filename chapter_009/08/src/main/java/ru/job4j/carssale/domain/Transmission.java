package ru.job4j.carssale.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author Dmitry Belokursky
 * @since 27.06.18.
 */

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transmission")
@DynamicUpdate
public class Transmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transmission_id")
    private Long id;

    @Column(name = "name")
    private String name;
}
