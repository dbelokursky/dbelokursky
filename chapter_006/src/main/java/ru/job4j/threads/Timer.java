package ru.job4j.threads;

/**
 * @author Dmitry Belokursky
 * @since 21.01.18.
 */
public class Timer implements Runnable {

    private final long timeout;

    private final Thread counter;

    public Timer(long timeout, Thread counter) {
        this.timeout = timeout;
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(timeout);
            counter.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
