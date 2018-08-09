package ru.job4j.storage;

public class JdbcStorage implements Storage {

    @Override
    public void add(User user) {
        System.out.printf("jdbc storage");
    }
}
