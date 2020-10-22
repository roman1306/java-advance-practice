package com.github.roman1306.task.threads;

import com.github.roman1306.task.entity.SlotForReservation;
import lombok.AllArgsConstructor;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.concurrent.BlockingQueue;

@AllArgsConstructor
public class ProducerForReservation implements Runnable {
    private BlockingQueue<SlotForReservation> queue;

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            try {
                queue.put(new SlotForReservation("Description", LocalDate.now(), "Hotel name"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
