package com.github.roman1306.transfer.Threads;

import com.github.roman1306.transfer.entity.Account;
import com.github.roman1306.transfer.service.AccountService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionMoney implements Runnable {
    private final Account from;
    private final Account to;
    private final long sum;

    @Override
    public void run() {
        AccountService.sendMoney(from, to, sum);

    }
}
