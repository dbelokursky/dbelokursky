package ru.job4j.generic;

import java.util.NoSuchElementException;

/**
 * @author Dmitry Belokursky
 * @since 21.10.17.
 */
public abstract class AbstractStore<T> {

    SimpleArray<T> store = new SimpleArray<>();

    public T add(T model) {
        store.add(model);
        return model;
    }

    public T update(T replaceableMod, T replacingMod) {
        if (store.update(replaceableMod, replacingMod)) {
            return replaceableMod;
        }
        throw new NoSuchElementException();
    }

    public boolean delete(String id) {
        return store.delete(id);
    }
}
