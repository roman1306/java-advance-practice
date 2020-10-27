package com.github.roman1306.transfer;

import com.github.roman1306.transfer.bank.Bank;
import com.github.roman1306.transfer.io.AccountReader;
import com.github.roman1306.transfer.io.AccountWriter;
import com.github.roman1306.transfer.repository.AccountRepository;
import com.github.roman1306.transfer.service.AccountService;
import com.github.roman1306.transfer.threads.RandomTransactionMoney;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Transfer {
    public static final String PATH = "money-transfer/src/main/resources/accounts";
    public static final int AMOUNT_THREADS = 1000;
    public static final int AMOUNT_ACTIVE_THREADS = 20;

    public static void main(String[] args) throws InterruptedException {

        AccountWriter accountWriter = new AccountWriter(PATH);
        AccountReader accountReader = new AccountReader(PATH);

        accountWriter.initAccount();

        System.out.println("Balance all accounts =" + accountReader.readSumBalancesAccounts());

        Bank bank = new Bank(accountReader, accountWriter, new AccountRepository());

        ExecutorService executorService = Executors.newFixedThreadPool(AMOUNT_ACTIVE_THREADS);

        for (int i = 0; i < AMOUNT_THREADS; i++) {
            executorService.submit(new RandomTransactionMoney(bank));
        }

        executorService.shutdown();

        executorService.awaitTermination(1, TimeUnit.HOURS);

        System.out.println("Balance all accounts = " + accountReader.readSumBalancesAccounts());

    }


}
