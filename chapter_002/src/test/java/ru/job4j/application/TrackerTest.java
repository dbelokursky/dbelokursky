package ru.job4j.application;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс TrackerTest. Для тестирования класса Tracker.
 * @author Dmitry Belokursky
 * @since 0.1 02.07.2017
 */
public class TrackerTest {

//    @Test
//    public void whenAddNewItemThanTrackerHasSameItem() {
//        Tracker tracker = new Tracker();
//        Item item = new Item("Test1", "Desc1");
//        tracker.add(item);
//        Item[] result = tracker.findByName("Test1");
//        assertThat(result[0], is(item));
//    }
//
//    @Test
//    public void whenUpdateItemsThanUpdatedItems() {
//        Tracker tracker = new Tracker();
//        Item item = new Item("Test1", "Desc1");
//        item.setId("1111");
//        tracker.add(item);
//        Item item1 = new Item("UpdatedTask", "Desc2");
//        item1.setId("1111");
//        tracker.update(item1);
//        Item[] result = tracker.findByName("UpdatedTask");
//        assertThat(result[0], is(item1));
//    }
//
//    @Test
//    public void whenDeleteItemThanItemsWithoutItem() {
//        Tracker tracker = new Tracker();
//        Item item = new Item("Test1", "Desc1");
//        item.setId("1111");
//        tracker.add(item);
//        Item item1 = new Item("Test2", "Desc2");
//        item1.setId("1111");
//        tracker.add(item1);
//        tracker.delete(item);
//        Item[] result = tracker.findByName("Test2");
//        assertThat(result[0], is(item1));
//    }
//
//    @Test
//    public void findAllTest() {
//        Tracker tracker = new Tracker();
//        Item item = new Item("Test1", "Desc1");
//        tracker.add(item);
//        Item item1 = new Item("Test2", "Desc2");
//        tracker.add(item1);
//        Item[] expected = {item, item1};
//        Item[] result = tracker.findAll();
//        assertThat(result, is(expected));
//    }
//
//    @Test
//    public void findByNameTest() {
//        Tracker tracker = new Tracker();
//        Item item = new Item("Test1", "Desc1");
//        tracker.add(item);
//        Item item1 = new Item("Test2", "Desc2");
//        tracker.add(item1);
//        Item[] expected = {item};
//        Item[] result = tracker.findByName("Test1");
//        assertThat(result, is(expected));
//    }
//
//    @Test
//    public void findByIdTest() {
//        Tracker tracker = new Tracker();
//        Item item = new Item("Test1", "Desc1");
//        item.setId("1");
//        tracker.add(item);
//        Item item1 = new Item("Test2", "Desc2");
//        tracker.add(item1);
//        Item expected = item;
//        Item result = tracker.findById("1");
//        assertThat(result, is(expected));
//    }
}