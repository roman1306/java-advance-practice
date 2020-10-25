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

        ExecutorService executorService = Executors.newFixedThreadPool(9);
        for (int i = 0; i < 3; i++) {
            executorService.submit(new ProducerRunnable(queue, i));
        }
        for (int i = 0; i < 6; i++) {
            executorService.submit(new ConsumerRunnable(queue, i));
        }

        executorService.shutdown();

        executorService.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("finish");

    }
}

