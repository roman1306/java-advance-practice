package com.github.roman1306.transfer.threads;

import com.github.roman1306.transfer.bank.Bank;
import lombok.AllArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
public class RandomTransactionMoney implements Runnable{

    private final Bank bank;

    @Override
    public void run() {
        bank.sendMoney(randomIdAccount(), randomIdAccount(), ThreadLocalRandom.current().nextLong(1000));
    }

    private String randomIdAccount() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(1, 11));
    }
}
