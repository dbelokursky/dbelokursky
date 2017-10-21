package ru.job4j.generic;

/**
 * @author Dmitry Belokursky
 * @since 20.10.17.
 */
public interface Store<T extends Base> {

    T add(T model);

    T update(T model, T updatedMod);

    boolean delete(String id);
}
