package ru.job4j.carssale.models;


import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ToString(exclude = "cars")
@Getter
@Setter
@EqualsAndHashCode(exclude = "cars")
@NoArgsConstructor
@Entity
@Table(name = "owner")
@DynamicUpdate
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private int id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Car> cars = new HashSet<>();

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;
}
