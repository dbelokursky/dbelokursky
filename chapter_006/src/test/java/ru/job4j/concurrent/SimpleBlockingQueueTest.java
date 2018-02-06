package ru.job4j.concurrent;

/**
 * @author Dmitry Belokursky
 * @since 06.02.18.
 */
public class SimpleBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    queue.offer(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    queue.pool();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
    }
}