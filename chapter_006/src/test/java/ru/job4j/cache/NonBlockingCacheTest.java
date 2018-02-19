package ru.job4j.cache;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 11.02.18.
 */
public class NonBlockingCacheTest {

    @Test
    public void addTest() {
        NonBlockingCache nbc = new NonBlockingCache();
        Task task = new Task("test");
        nbc.add(task);
        Task expected = task;
        Task result = nbc.get("test");
        assertThat(result, is(expected));
    }

    @Test
    public void updateTest() throws OptimisticException {
        NonBlockingCache nbc = new NonBlockingCache();
        Task task = new Task("test");
        nbc.add(task);
        nbc.update("test", "new desc");
        String expected = "new desc";
        String result = nbc.get("test").getDescription();
        assertThat(result, is(expected));
    }

    @Test
    public void deleteTest() {
        NonBlockingCache nbc = new NonBlockingCache();
        Task task = new Task("test");
        nbc.add(task);
        nbc.delete(task);
        Task result = nbc.get("test");
        Task expected = null;
        assertThat(result, is(expected));
    }
}