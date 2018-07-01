package ru.job4j.todo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @author Dmitry Belokursky
 * @since 10.06.18.
 */

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Item {

    private int id;

    private String description;

    private Timestamp created;

    private boolean done;
}
