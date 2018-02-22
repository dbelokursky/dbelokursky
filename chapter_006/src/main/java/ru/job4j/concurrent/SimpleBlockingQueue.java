package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Dmitry Belokursky
 * @since 06.02.18.
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    private final int capacity;

    private Queue<T> queue;

    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (queue.size() == capacity) {
                this.wait();
            }
            queue.add(value);
            this.notify();
        }
    }

    public T pool() throws InterruptedException {
        T result;
        synchronized (this) {
            while (queue.size() == 0) {
                this.wait();
            }
            result = queue.poll();
            this.notify();
        }
        return result;
    }
}
