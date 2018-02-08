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
    private boolean locked;

    public SimpleLock() {
        this.locked = false;
    }

    public synchronized void lock() throws InterruptedException {
        while (this.locked) {
            this.wait();
        }
        this.locked = true;
    }

    public synchronized void unlock() {
        this.locked = false;
        notifyAll();
    }
}
