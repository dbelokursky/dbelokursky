package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

/**
 * @author Dmitry Belokursky
 * @since 07.02.18.
 */
@ThreadSafe
public class ThreadPool implements Executor {

    private final int availableProcessors;

    private final Queue<Runnable> workQueue;

    private volatile boolean isRunning;

    public ThreadPool() {
        this.availableProcessors = Runtime.getRuntime().availableProcessors();
        this.workQueue = new ConcurrentLinkedQueue<>();
        this.isRunning = true;

        for (int i = 0; i < availableProcessors; i++) {
            new Thread(new Worker()).start();
        }
    }

    public void add(Runnable work) {
        execute(work);
    }

    public void shutdown() {
        isRunning = false;
    }

    @Override
    public void execute(Runnable work) {
        if (isRunning) {
            workQueue.offer(work);
        }
    }

    private final class Worker implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                Runnable nextTask = workQueue.poll();
                if (nextTask != null) {
                    nextTask.run();
                }
            }
        }
    }
}
