package ru.job4j.cache;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Dmitry Belokursky
 * @since 10.02.18.
 */
@ThreadSafe
public class NonBlockingCache {

    private final ConcurrentHashMap<String, Task> cache;

    public NonBlockingCache() {
        this.cache = new ConcurrentHashMap<>();
    }

    public Task add(Task task) {
        return cache.putIfAbsent(task.getName(), task);
    }

    public Task update(String name, String description) throws OptimisticException {
        Task task = cache.get(name);
        int expectedVersion = task.getVersion().get();
        if (expectedVersion == (cache.get(name).getVersion().get())) {
            return cache.computeIfPresent(task.getName(), (k, v) -> new Task(task.getName(), description, v.getVersion().incrementAndGet()));
        } else {
            throw new OptimisticException("Concurrent modification");
        }
    }

    public boolean delete(Task task) {
        return cache.remove(task.getName(), task);
    }

    public Task get(String name) {
        return cache.get(name);
    }

    @Override
    public synchronized String toString() {
        return "NonBlockingCache{cache=" + cache + '}';
    }
}
