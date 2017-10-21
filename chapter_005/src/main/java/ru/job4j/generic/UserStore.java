package ru.job4j.generic;

/**
 * @author Dmitry Belokursky
 * @since 20.10.17.
 */
public class UserStore extends AbstractStore<User> {

    public boolean delete(String id) {
        boolean result = false;
        if (store.delete(new User(id))) {
            result = true;
        }
        return result;
    }
}
