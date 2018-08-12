package ru.job4j.storage.models;

import lombok.*;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Setter
@Getter
public class User {

    private int id;

    private String name;

    private String password;
}
