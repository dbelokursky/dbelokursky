package ru.job4j.storage;

public class MemoryStorage implements Storage {

    @Override
    public void add(User user) {
        System.out.println("memory storage");
    }
}
