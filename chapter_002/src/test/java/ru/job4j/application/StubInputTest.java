package ru.job4j.application;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 06.07.17.
 */
public class StubInputTest {

//    @Test
//    public void whenAddItemTrackerHasItemWithTheSameName() {
//        Tracker tracker = new Tracker();
//        ArrayList<String> inp = new ArrayList<>();
//        inp.add("0");
//        inp.add("test name");
//        inp.add("desc");
//        inp.add("6");
//        Input input = new StubInput(inp);
//        new StartUI(input, tracker).init();
//        assertThat(tracker.findAll()[0].getName(), is("test name"));
//    }
//
//    @Test
//    public void whenAddItemThenTrackerHasOneItem() {
//        Tracker tracker = new Tracker();
//        Input input = new StubInput(new ArrayList<String>() {{
//            add("1");
//            add("6");
//        }});
//        Item item = new Item("Name", "Desc");
//        tracker.add(item);
//        new StartUI(input, tracker).init();
//        assertThat(tracker.findAll().length, is(1));
//    }
//
//    @Test
//    public void whenUpdateItemThenTrackerHasUpdatedItem() {
//        Tracker tracker = new Tracker();
//        Item item = new Item("Name", "Desc");
//        tracker.add(item);
//        ArrayList<String> inp = new ArrayList<>();
//        inp.add("2");
//        inp.add(item.getId());
//        inp.add("test name");
//        inp.add("6");
//        Input input = new StubInput(inp);
//        new StartUI(input, tracker).init();
//        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
//    }
//
//    @Test
//    public void whenRemoveItemThenNoItemInTracker() {
//        Tracker tracker = new Tracker();
//        Item item = new Item("Name", "Desc");
//        tracker.add(item);
//        ArrayList<String> inp = new ArrayList<>();
//        inp.add("3");
//        inp.add(item.getId());
//        inp.add("6");
//        Input input = new StubInput(inp);
//        new StartUI(input, tracker).init();
//        assertThat(tracker.findAll().length, is(0));
//    }
//
//    @Test
//    public void findByIdTest() {
//        Tracker tracker = new Tracker();
//        Item item = new Item("Name", "Desc");
//        tracker.add(item);
//        String id = item.getId();
//        ArrayList<String> inp = new ArrayList<>();
//        inp.add("4");
//        inp.add(item.getId());
//        inp.add("6");
//        Input input = new StubInput(inp);
//        new StartUI(input, tracker).init();
//        assertThat(tracker.findById(item.getId()), is(item));
//    }
}
