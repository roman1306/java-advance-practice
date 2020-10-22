package com.github.roman1306.task.threads;

import com.github.roman1306.task.entity.Book;
import com.github.roman1306.task.queue.MyQueue;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class ProducerRunnable implements Runnable {
    MyQueue queue;
    int id;

    public ProducerRunnable(MyQueue queue, int id) {
        this.queue = queue;
        this.id = id;
        queue.addProducer(this);
    }

    @Override
    public void run() {
        Book slot;

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1500);
                slot = new Book(
                        "Description",
                        LocalDate.now().plusDays(ThreadLocalRandom.current().nextInt(7, 150)),
                        "Hotel");
                queue.push(slot);
                System.out.println("Producer № " + id + " add " + slot);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        queue.removeProducer(this);
    }
}
