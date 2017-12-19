package ru.job4j.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class TreeTest {
    @Test
    public void add() throws Exception {
        Tree stree = new Tree<>();
        stree.addFirst(1);
        System.out.println(stree);
        stree.add(1, 2);
        System.out.println(stree);
        stree.add(2, 3);
        stree.add(3, 4);
        stree.add(4, 5);
        System.out.println(stree);
        System.out.println("---------------");
    }

    @Test
    public void iterator() throws Exception {
    }

}