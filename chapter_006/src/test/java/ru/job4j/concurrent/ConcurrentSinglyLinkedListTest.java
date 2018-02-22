package ru.job4j.concurrent;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Dmitry Belokursky
 * @since 03.02.18.
 */
public class ConcurrentSinglyLinkedListTest {

    @Test
    public void addTest() throws Exception {
        ConcurrentSinglyLinkedList<String> dll = new ConcurrentSinglyLinkedList<>();
        dll.add("0");
        String result = dll.get(0);
        String expected = "0";
        assertThat(result, is(expected));
    }

    @Test
    public void addFirstTest() throws Exception {
        ConcurrentSinglyLinkedList<String> dll = new ConcurrentSinglyLinkedList<>();
        dll.addFirst("0");
        dll.addFirst("1");
        dll.addFirst("2");
        dll.addFirst("3");
        dll.addFirst("4");
        dll.addFirst("5");
        dll.removeLast();
        String result = dll.get(0);
        String expected = "5";
        assertThat(result, is(expected));
    }

    @Test
    public void addLastTest() throws Exception {
        ConcurrentSinglyLinkedList<String> dll = new ConcurrentSinglyLinkedList<>();
        dll.addLast("0");
        dll.addLast("1");
        dll.addLast("2");
        dll.addLast("3");
        dll.addLast("4");
        dll.addLast("5");
        String result = dll.get(0);
        String expected = "0";
        assertThat(result, is(expected));
    }

    @Test
    public void getTest() throws Exception {
        ConcurrentSinglyLinkedList<String> dll = new ConcurrentSinglyLinkedList<>();
        dll.addLast("0");
        dll.addLast("1");
        dll.addLast("2");
        dll.addLast("3");
        dll.addLast("4");
        dll.addLast("5");
        String result = dll.get(3);
        String expected = "3";
        assertThat(result, is(expected));
    }

    @Test
    public void iteratorTest() throws Exception {
        ConcurrentSinglyLinkedList<String> dll = new ConcurrentSinglyLinkedList<>();
        dll.addLast("0");
        dll.addLast("1");
        dll.addLast("2");
        dll.addLast("3");
        dll.addLast("4");
        dll.addLast("5");
        dll.removeLast();
        Iterator<String> it = dll.iterator();
        String result = it.next();
        String expected = "0";
        assertThat(result, is(expected));
    }
}