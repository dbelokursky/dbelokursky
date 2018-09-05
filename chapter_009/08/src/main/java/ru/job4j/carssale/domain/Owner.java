package ru.job4j.carssale.domain;


import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "owner")
@DynamicUpdate
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Car> cars = new HashSet<>();

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "owner_role",
            joinColumns = {@JoinColumn(name = "owner_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    @EqualsAndHashCode.Exclude
    private Set<Role> roles = new HashSet<>();
}
