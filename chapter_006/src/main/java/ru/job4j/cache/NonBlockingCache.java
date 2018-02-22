package ru.job4j.cache;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Dmitry Belokursky
 * @since 10.02.18.
 */
@ThreadSafe
public class NonBlockingCache {

    private ConcurrentHashMap<String, Task> cache;

    public NonBlockingCache() {
        this.cache = new ConcurrentHashMap<>();
    }

    public Task add(Task task) {
        return cache.putIfAbsent(task.getName(), task);
    }

    public Task update(String name, String description) throws OptimisticException {
        Task oldTask = cache.get(name);
        int oldTaskVersion = oldTask.getVersion();
        Task newTask = new Task(name, description, oldTaskVersion + 1);
        Task result = cache.computeIfPresent(name, (k, v) -> cache.get(name).getVersion() == oldTaskVersion ? newTask : null);
        if (result != null) {
            return result;
        } else {
            throw new OptimisticException("Unsuccessful update.");
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
