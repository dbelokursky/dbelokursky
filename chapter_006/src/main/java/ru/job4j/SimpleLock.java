package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author Dmitry Belokursky
 * @since 09.02.18.
 */
@ThreadSafe
public class SimpleLock {

    @GuardedBy("this")
    private boolean isLocked;

    @GuardedBy("this")
    private Thread currentThread;

    public SimpleLock() {
        this.isLocked = false;
    }

    public synchronized void lock() throws InterruptedException {
        this.currentThread = Thread.currentThread();
        while (this.isLocked) {
            this.wait();
        }
        this.currentThread = Thread.currentThread();
        this.isLocked = true;
    }

    public synchronized void unlock() {
        if (this.isLocked && currentThread.equals(Thread.currentThread())) {
            this.isLocked = false;
            currentThread = null;
            notifyAll();
        }
    }
}