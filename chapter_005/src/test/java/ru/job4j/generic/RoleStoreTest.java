package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 21.10.17.
 */
public class RoleStoreTest {

    @Test
    public void add() throws Exception {
        RoleStore roleStore = new RoleStore();
        Role result = roleStore.add(new Role("test1"));
        Role expected = new Role("test1");
        assertThat(result, is(expected));
    }

    @Test
    public void update() throws Exception {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("test2"));
        Role result = roleStore.update(new Role("test2"), new Role("test2Updated"));
        Role expected = new Role("test2");
        assertThat(result, is(expected));
    }

    @Test
    public void delete() throws Exception {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("test3"));
        boolean result = roleStore.delete("test3");
        boolean expected = true;
        assertThat(result, is(expected));
    }

}