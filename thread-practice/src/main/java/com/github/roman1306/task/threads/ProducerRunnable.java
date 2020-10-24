package com.github.roman1306.task.threads;

import com.github.roman1306.task.entity.Book;
import com.github.roman1306.task.queue.MyQueue;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class ProducerRunnable implements Runnable {
    private final Logger logger = Logger.getLogger("Producer");
    MyQueue queue;
    int id;

    public ProducerRunnable(MyQueue queue, int id) {
        this.queue = queue;
        this.id = id;
        queue.addProducer();
    }

    @Override
    public void run() {
        Book slot;
        String message;

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1500);
                slot = new Book(
                        "Description",
                        LocalDate.now().plusDays(ThreadLocalRandom.current().nextInt(7, 150)),
                        "Hotel");
                queue.push(slot);
                message = "Producer № " + id + " add " + slot;
                logger.info(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        queue.removeProducer();
    }
}
