package com.github.roman1306.task;

import com.github.roman1306.task.entity.SlotForReservation;
import com.github.roman1306.task.threads.ConsumerForReservation;
import com.github.roman1306.task.threads.ProducerForReservation;

import java.util.concurrent.*;

public class Application {
    private static BlockingQueue<SlotForReservation> queue = new LinkedBlockingQueue<>(5);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new ProducerForReservation(queue));
        executorService.submit(new ConsumerForReservation(queue));
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
    }
}

