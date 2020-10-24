package com.github.roman1306.task.threads;

import com.github.roman1306.task.entity.Book;
import com.github.roman1306.task.queue.MyQueue;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class ConsumerRunnable implements Runnable {
    private final Logger logger = Logger.getLogger("Consumer");
    MyQueue queue;
    int id;

    public ConsumerRunnable(MyQueue queue, int id) {
        this.queue = queue;
        this.id = id;
        queue.addConsumer();
    }

    @Override
    public void run() {
        Book slot;
        String message;

        while (queue.hasProducer() || !queue.isEmpty()) {
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(2));
                slot = queue.pop();

                if (slot != null) {
                    message = "Consumer № " + id + " get " + slot;
                    logger.info(message);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        queue.removeConsumer();
    }
}
