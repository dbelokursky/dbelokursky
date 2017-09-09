package ru.job4j.user;


/**
 * @author Dmitry Belokursky
 * @since 03.09.17.
 */

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {

    private UserConvert uc = new UserConvert();

    private List<User> users = new ArrayList<>();

    @Before
    public void before() {
        User mike = new User(1, "Mike", "Boston");
        User tom = new User(2, "Tom", "Chicago");
        users = Arrays.asList(mike, tom);
    }

    @Test
    public void processTest() {
        HashMap<Integer, User> expected = new HashMap<>();
        expected.put(1, new User(1, "Mike", "Boston"));
        expected.put(2, new User(2, "Tom", "Chicago"));
        HashMap<Integer, User> result = uc.process(users);
        assertThat(result, is(expected));
    }
}
