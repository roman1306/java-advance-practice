package com.github.roman1306.task.threads;

import com.github.roman1306.task.entity.Book;
import com.github.roman1306.task.queue.MyQueue;

import java.util.concurrent.TimeUnit;


public class ConsumerRunnable implements Runnable {
    MyQueue queue;
    int id;

    public ConsumerRunnable(MyQueue queue, int id) {
        this.queue = queue;
        this.id = id;
        queue.addConsumer(this);
    }

    @Override
    public void run() {

        while (queue.hasProducer() || !queue.isEmpty()) {
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(2));
                Book slot = queue.pop();

                if (slot == null) {
                    System.out.println("Consumer № " + id + " get " + slot);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        queue.removeConsumer(this);
    }
}
