package ru.job4j.cache;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Dmitry Belokursky
 * @since 11.02.18.
 */
@ThreadSafe
public final class Task {

    @GuardedBy("this")
    private final String name;

    @GuardedBy("this")
    private final String description;

    @GuardedBy("this")
    private final AtomicInteger version;

    public Task(String name) {
        this.name = name;
        this.version = new AtomicInteger(0);
        this.description = "";
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.version = new AtomicInteger(0);
    }

    public Task(String name, String description, int version) {
        this.name = name;
        this.description = description;
        this.version = new AtomicInteger(version);
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized String getDescription() {
        return description;
    }

    public synchronized AtomicInteger getVersion() {
        return version;
    }

    @Override
    public synchronized boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(getName(), task.getName())
                && Objects.equals(getDescription(), task.getDescription())
                && Objects.equals(getVersion(), task.getVersion());
    }

    @Override
    public synchronized int hashCode() {
        return Objects.hash(getName(), getDescription(), getVersion());
    }

    @Override
    public synchronized String toString() {
        return "Task{ name='" + name + ", description=" + description + ", version=" + version + '}';
    }
}
