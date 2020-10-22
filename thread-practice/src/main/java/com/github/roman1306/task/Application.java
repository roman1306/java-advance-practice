package com.github.roman1306.task;


import com.github.roman1306.task.queue.MyQueue;
import com.github.roman1306.task.threads.ConsumerRunnable;
import com.github.roman1306.task.threads.ProducerRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Application {

    public static void main(String[] args) throws InterruptedException {
        MyQueue queue = new MyQueue(5);

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        executorService.submit(new ProducerRunnable(queue, 1));
        executorService.submit(new ConsumerRunnable(queue, 1));
        executorService.submit(new ProducerRunnable(queue, 2));
        executorService.submit(new ConsumerRunnable(queue, 2));
        executorService.submit(new ProducerRunnable(queue, 3));
        executorService.submit(new ConsumerRunnable(queue, 3));
        executorService.submit(new ConsumerRunnable(queue, 4));
        executorService.submit(new ConsumerRunnable(queue, 5));
        executorService.submit(new ConsumerRunnable(queue, 6));

        executorService.shutdown();

        executorService.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("finish");

    }
}

