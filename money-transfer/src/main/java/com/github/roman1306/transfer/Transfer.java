package com.github.roman1306.transfer;

import com.github.roman1306.transfer.bank.Bank;
import com.github.roman1306.transfer.entity.Account;
import com.github.roman1306.transfer.io.AccountReader;
import com.github.roman1306.transfer.io.AccountWriter;
import com.github.roman1306.transfer.repository.AccountRepository;
import com.github.roman1306.transfer.threads.TransactionMoney;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Transfer {

    public static void main(String[] args) throws InterruptedException {

        AccountWriter accountWriter = new AccountWriter("money-transfer/src/main/resources/accounts");
        AccountReader accountReader = new AccountReader("money-transfer/src/main/resources/accounts");

        Bank bank = new Bank(accountReader, accountWriter, new AccountRepository());

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 1000; i++) {
            executorService.submit(new TransactionMoney(
                    String.valueOf(ThreadLocalRandom.current().nextInt(1, 11)),
                    String.valueOf(ThreadLocalRandom.current().nextInt(1, 11)),
                    ThreadLocalRandom.current().nextLong(1000),
                    bank));
        }

        executorService.shutdown();

        executorService.awaitTermination(1, TimeUnit.HOURS);

        long balance = 0;

        for (Account account : accountReader.readAllAccounts()) {
            System.out.println(account);
            balance += account.getBalance();
        }
        System.out.println(balance);
    }

    private static void initAccount(AccountWriter accountWriter) {
        for (int i = 1; i <= 10; i++) {
            accountWriter.writeAccount(new Account(String.valueOf(i), 5000));
        }
    }
}
