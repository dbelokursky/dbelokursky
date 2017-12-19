package ru.job4j.tree;

import org.junit.Test;

/**
 * @author Dmitry Belokursky
 * @since 26.11.17.
 */
public class SimpleBSTTest {

    @Test
    public void add() throws Exception {
        SimpleBST simpleBST = new SimpleBST();
        simpleBST.add(10);
        simpleBST.add(2);
        System.out.println(simpleBST.hasNext());
        System.out.println(simpleBST.next());
        System.out.println(simpleBST.next());
        System.out.println(simpleBST.hasNext());
    }
}