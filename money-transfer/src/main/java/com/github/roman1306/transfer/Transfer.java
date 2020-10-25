package com.github.roman1306.transfer;

import com.github.roman1306.transfer.Threads.TransactionMoney;
import com.github.roman1306.transfer.entity.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Transfer {

    public static void main(String[] args) throws InterruptedException {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("1", 5000));
        accounts.add(new Account("2", 5000));
        accounts.add(new Account("3", 5000));
        accounts.add(new Account("4", 5000));
        accounts.add(new Account("5", 5000));

        ExecutorService executorService = Executors.newFixedThreadPool( 20);

        for (int i = 0; i < 1000; i++) {
            executorService.submit(new TransactionMoney(
                    accounts.get(ThreadLocalRandom.current().nextInt(5)),
                    accounts.get(ThreadLocalRandom.current().nextInt(5)),
                    ThreadLocalRandom.current().nextLong(1000)
            ));
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);

        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}
