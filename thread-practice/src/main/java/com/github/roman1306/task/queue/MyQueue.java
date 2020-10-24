package com.github.roman1306.task.queue;

import com.github.roman1306.task.entity.Book;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {

    private Queue<Book> queue = new LinkedList<>();
    private final int maxCapacity;

    AtomicInteger countProducer = new AtomicInteger(0);
    AtomicInteger countConsumer = new AtomicInteger(0);

    public MyQueue(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public synchronized void push(Book slot) throws InterruptedException {
        while (queue.size() == maxCapacity && hasConsumer()) {
            wait();
        }
        queue.add(slot);
        notifyAll();
    }

    public synchronized Book pop() throws InterruptedException {
        while (queue.isEmpty() && hasProducer()) {
            wait();
        }
        Book slot = queue.poll();
        notifyAll();
        return slot;
    }

    public synchronized void addProducer() {
        countProducer.incrementAndGet();
    }

    public synchronized void removeProducer() {
        countProducer.decrementAndGet();
    }

    public synchronized boolean hasProducer() {
        return countProducer.get() != 0;
    }

    public synchronized void addConsumer() {
        countConsumer.incrementAndGet();
    }

    public synchronized void removeConsumer() {
        countConsumer.decrementAndGet();
    }

    synchronized boolean hasConsumer() {
        return countConsumer.get() != 0;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
