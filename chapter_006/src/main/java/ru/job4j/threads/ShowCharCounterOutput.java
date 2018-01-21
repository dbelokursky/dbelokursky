package ru.job4j.threads;

/**
 * @author Dmitry Belokursky
 * @since 21.01.18.
 */
public class ShowCharCounterOutput {

    public static void main(String[] args) throws InterruptedException {
        Thread counter = new Thread(new CharCounter());
        Thread timer = new Thread(new Timer(100, counter));
        timer.start();
        counter.start();
    }
}
