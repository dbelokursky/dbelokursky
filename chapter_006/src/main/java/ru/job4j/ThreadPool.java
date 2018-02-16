package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Dmitry Belokursky
 * @since 07.02.18.
 */
@ThreadSafe
public class ThreadPool {

    private final int availableProcessors;

    private final Queue<Runnable> workQueue;

    private volatile boolean isRunning;

    public ThreadPool() {
        this.availableProcessors = Runtime.getRuntime().availableProcessors();
        this.workQueue = new LinkedBlockingQueue<>();
        this.isRunning = true;
    }

    public void init() {
        for (int i = 0; i < availableProcessors; i++) {
            new Thread(new Worker()).start();
        }
    }

    public void add(Runnable work) {
        if (isRunning) {
            workQueue.offer(work);
            notifyAll();
        }
    }

    public void shutdown() {
        isRunning = false;
    }

    private final class Worker implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                if (workQueue.size() == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    workQueue.poll().run();
                }
            }
        }
    }
}
