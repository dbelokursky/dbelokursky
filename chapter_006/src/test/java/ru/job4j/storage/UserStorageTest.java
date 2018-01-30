package ru.job4j.storage;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 26.01.18.
 */
public class UserStorageTest {

    @Test
    public void addTest() {
        UserStorage userStorage = new UserStorage();
        User firstUser = new User(1, 200);
        User secondUser = new User(2, 200);
        User thirdUser = new User(3, 400);
        userStorage.add(firstUser);
        userStorage.add(secondUser);
        userStorage.add(thirdUser);

        int expected = 3;
        int result = userStorage.size();

        assertThat(result, is(expected));
    }

    @Test
    public void updateTest() {
        UserStorage userStorage = new UserStorage();
        User firstUser = new User(1, 200);
        User secondUser = new User(2, 200);
        User thirdUser = new User(3, 400);
        userStorage.add(firstUser);
        userStorage.add(secondUser);
        userStorage.add(thirdUser);

        User expected = new User(1, 300);
        userStorage.update(expected);
        User result = userStorage.getById(1);
        assertThat(result, is(expected));
    }

    @Test
    public void deleteTest() {
        UserStorage userStorage = new UserStorage();
        User firstUser = new User(1, 200);
        User secondUser = new User(2, 200);
        User thirdUser = new User(3, 400);
        userStorage.add(firstUser);
        userStorage.add(secondUser);
        userStorage.add(thirdUser);
        User delUser = new User(1, 200);
        userStorage.delete(delUser);

        User expected = null;
        User result = userStorage.getById(1);

        assertThat(result, is(expected));
    }

    @Test
    public void transferTest() {
        UserStorage userStorage = new UserStorage();
        User firstUser = new User(1, 200);
        User secondUser = new User(2, 200);
        User thirdUser = new User(3, 400);
        userStorage.add(firstUser);
        userStorage.add(secondUser);
        userStorage.add(thirdUser);

        userStorage.transfer(2, 3, 100);
        int expected = 500;
        int result = userStorage.getById(3).getAmount();

        assertThat(result, is(expected));
    }
}