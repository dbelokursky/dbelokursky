package ru.job4j.carssale.models;

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
@Table(name = "suspension")
@DynamicUpdate
public class Suspension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "suspension_id")
    private Long id;

    @Column(name = "name")
    private String name;
}
