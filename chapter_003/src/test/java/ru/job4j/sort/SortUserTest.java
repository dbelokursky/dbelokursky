package ru.job4j.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Dmitry Belokursky
 * @since 03.09.17.
 */
public class SortUserTest {

    @Test
    public void sortTest() {
        SortUser sortUser = new SortUser();
        List<User> list = Arrays.asList(new User("Mike", 22),
                new User("John", 21),
                new User("Phil", 19));
        TreeSet<User> result = sortUser.sort(list);
        TreeSet<User> expected = new TreeSet<>();
        expected.add(new User("Phil", 19));
        expected.add(new User("Phil", 19));
    }
}
