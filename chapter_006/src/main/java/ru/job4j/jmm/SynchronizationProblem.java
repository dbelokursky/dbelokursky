package ru.job4j.jmm;

import java.util.concurrent.TimeUnit;

/**
 * @author Dmitry Belokursky
 * @since 31.01.18.
 * Joshua Bloch Effective java 2
 */
public class SynchronizationProblem {

    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                i++;
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}