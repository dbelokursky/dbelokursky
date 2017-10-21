package ru.job4j.generic;

/**
 * @author Dmitry Belokursky
 * @since 20.10.17.
 */
public class RoleStore extends AbstractStore<Role> {

    public boolean delete(String id) {
        boolean result = false;
        if (store.delete(new Role(id))) {
            result = true;
        }
        return result;
    }
}
