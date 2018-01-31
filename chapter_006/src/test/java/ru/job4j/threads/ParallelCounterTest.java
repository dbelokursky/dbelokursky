package ru.job4j.threads;

import static org.junit.Assert.*;

public class ParallelCounterTest {

    @org.junit.Test
    public void count() {
        ParallelCounter pc = new ParallelCounter();
        pc.count();
    }
}