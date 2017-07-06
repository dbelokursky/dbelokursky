package ru.job4j.application;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 06.07.17.
 */
public class StubInputTest {

    @Test
    public void whenAddItemTrackerHasItemWithTheSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenAddItemThenTrackerHasOneItem() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"1", "6"});
        Item item = new Item("Name", "Desc");
        tracker.add(item);
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(1));
    }

    @Test
    public void whenUpdateItemThenTrackerHasUpdatedItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("Name", "Desc");
        tracker.add(item);
        Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    @Test
    public void whenRemoveItemThenNoItemInTracker() {
        Tracker tracker = new Tracker();
        Item item = new Item("Name", "Desc");
        tracker.add(item);
        Input input = new StubInput(new String[]{"3", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(0));
    }

    @Test
    public void findByIdTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("Name", "Desc");
        tracker.add(item);
        String id = item.getId();
        Input input = new StubInput(new String[]{"4", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()), is(item));
    }
}
