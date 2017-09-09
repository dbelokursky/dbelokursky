package ru.job4j.sort;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 09.09.17.
 */
public class SortUserTest {

    private SortUser sortUser = new SortUser();

    private List<User> users = new ArrayList<>();

    private List<User> expected = new ArrayList<>();

    @Before
    public void before() {
        User mike = new User("Mike", 33);
        User tom = new User("Tom", 45);
        User michael = new User("Michael", 44);
        users = Arrays.asList(mike, tom, michael);
    }

    @Test
    public void sortNameLengthTest() {
        List<User> result = sortUser.sortNameLength(users);
        expected = Arrays.asList(new User("Michael", 44),
                new User("Mike", 33),
                new User("Tom", 45));
        assertThat(result, is(expected));
    }

    @Test
    public void sortByAllFieldsTest() {
        List<User> unsorted = new ArrayList<>();
        unsorted = Arrays.asList(new User("Mike", 44),
                new User("Michael", 44),
                new User("Mike", 33),
                new User("Michael", 46),
                new User("Tom", 45));
        List<User> result = sortUser.sortByAllFields(unsorted);
        expected = Arrays.asList(new User("Michael", 44),
                new User("Michael", 46),
                new User("Mike", 33),
                new User("Mike", 44),
                new User("Tom", 45));
        assertThat(result, is(expected));
    }
}
