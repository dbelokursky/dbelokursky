package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 21.10.17.
 */
public class UserStoreTest {

    @Test
    public void add() throws Exception {
        UserStore userStore = new UserStore();
        User result = userStore.add(new User("test1"));
        User expected = new User("test1");
        assertThat(result, is(expected));
    }

    @Test
    public void update() throws Exception {
        UserStore roleStore = new UserStore();
        roleStore.add(new User("test2"));
        User result = roleStore.update(new User("test2"), new User("test2Updated"));
        User expected = new User("test2");
        assertThat(result, is(expected));
    }

    @Test
    public void delete() throws Exception {
        UserStore userStore = new UserStore();
        userStore.add(new User("test3"));
        boolean result = userStore.delete("test3");
        boolean expected = true;
        assertThat(result, is(expected));
    }

}