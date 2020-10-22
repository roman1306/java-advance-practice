package com.github.roman1306.task.threads;

import com.github.roman1306.task.entity.SlotForReservation;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.concurrent.BlockingQueue;

@AllArgsConstructor
public class ConsumerForReservation implements Runnable{
    BlockingQueue<SlotForReservation> queue;

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
