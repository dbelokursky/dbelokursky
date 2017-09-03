package ru.job4j.sort;

import java.util.List;
import java.util.TreeSet;

/**
 * @author Dmitry Belokursky
 * @since 03.09.17.
 */
public class SortUser {

    public TreeSet<User> sort(List<User> list) {
        TreeSet<User> result = new TreeSet<>();
        result.addAll(list);
        return result;
    }
}
