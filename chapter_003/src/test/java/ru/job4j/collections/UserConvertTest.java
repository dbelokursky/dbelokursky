package ru.job4j.collections;


/**
 * @author Dmitry Belokursky
 * @since 03.09.17.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {

    @Test
    public void processTest() {
        UserConvert uc = new UserConvert();
        List<User> users = new ArrayList<>();
        User mike = new User(1, "Mike", "Boston");
        User tom = new User(2, "Tom", "Chicago");
        users.add(mike);
        users.add(tom);
        HashMap<Integer, User> expected = new HashMap<>();
        expected.put(1, mike);
        expected.put(2, tom);
        HashMap<Integer, User> result = uc.process(users);
        assertThat(result, is(expected));
    }
}
