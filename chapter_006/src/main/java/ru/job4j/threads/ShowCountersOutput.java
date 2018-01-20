package ru.job4j.threads;

/**
 * @author Dmitry Belokursky
 * @since 20.01.18.
 */
public class ShowCountersOutput {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start");
        Thread fThread = new Thread(new SpacesCounter());
        Thread sThread = new Thread(new WordsCounter());
        fThread.start();
        sThread.start();
        sThread.join();
        fThread.join();
        System.out.println("END");
    }
}
