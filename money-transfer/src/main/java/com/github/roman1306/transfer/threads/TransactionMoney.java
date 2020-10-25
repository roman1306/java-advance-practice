package com.github.roman1306.transfer.threads;

import com.github.roman1306.transfer.bank.Bank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionMoney implements Runnable {
    private final String from;
    private final String to;
    private final long sum;
    private final Bank bank;

    @Override
    public void run() {
        bank.sendMoney(from, to, sum);
    }
}
