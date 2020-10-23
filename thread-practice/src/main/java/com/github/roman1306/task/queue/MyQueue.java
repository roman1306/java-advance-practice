package com.github.roman1306.task.queue;

import com.github.roman1306.task.entity.Book;
import com.github.roman1306.task.threads.ConsumerRunnable;
import com.github.roman1306.task.threads.ProducerRunnable;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MyQueue {

    private Queue<Book> queue = new LinkedList<>();
    private final int maxCapacity;

    List<ProducerRunnable> producerList = new LinkedList<>();
    List<ConsumerRunnable> consumerList = new LinkedList<>();

    public MyQueue(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public synchronized void push(Book slot) throws InterruptedException {
        while (queue.size() == maxCapacity) {
            if (hasConsumer())
                wait();
            else
                break;
        }
        queue.add(slot);
        notify();
    }

    public synchronized Book pop() throws InterruptedException {
        while (queue.isEmpty()) {
            if (hasProducer())
                wait();
            else
                break;
        }
        Book slot = queue.poll();
        notify();
        return slot;
    }

    public synchronized void addProducer(ProducerRunnable producer) {
        producerList.add(producer);
    }

    public synchronized void removeProducer(ProducerRunnable producer) {
        producerList.remove(producer);
    }

    public synchronized boolean hasProducer() {
        return !producerList.isEmpty();
    }

    public synchronized void addConsumer(ConsumerRunnable consumer) {
        consumerList.add(consumer);
    }

    public synchronized void removeConsumer(ConsumerRunnable consumer) {
        consumerList.remove(consumer);
    }

    synchronized boolean hasConsumer() {
        return !consumerList.isEmpty();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
